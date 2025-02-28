# Recime API

A Java application using the Spring framework that allows the ability to:
1. View a list of recently trending recipes
2. Ability to filter the trending recipes by difficulty

## Pre-planning
**Out of scope:**
- For this project is the ability to upload recipes and persist their data. 
- For simplicity I have only included the columns in the `recipe` table that are displayed on the image in the challenge description:
    ```
    title
    image_url
    difficulty
    ```
  Other fields that may be included could be:
  - user id who created the recipe
    - date that it was uploaded
    - ingredients (separate table for data normalization)
    - method (separate table for data normalization)

I have separated the trending recipes into its own table. Why?:
1. **Separation of Concerns** - `recipe` table focuses on the data for the recipe whilst `trending_recipes` contains the data allowing us to handle the logic and data related to trending recipes (for example ordering by `priority`) 
2. **Back office support** - admin can go in and adjust trending status as they wish
3. **Future enhancements** - separating trending recipes could allow us to integrate ML and recommend trending recipes based on user behaviour (out of scope for this project)

### ORMS

I haven't used ORM's a lot in my time as a software engineer but I was learning as I was going along in this project and decided to implement Hibernate to align more with the ReciMe code base (according to the job application description and usage of Hibernate).

When using it I come across a few pros and cons in the process:

**Pros:**
- Abstracting the SQL layer enhances readability and saves a lot of setting up
- If we wanted to port to another DB (say MySQL) we will be able to do it with minimal code changes

**Cons:**
- Little things like mapping a database enum to a Java enum proved to be quite frustrating 😂 until I finally came across this [thread](https://stackoverflow.com/questions/27804069/hibernate-mapping-between-postgresql-enum-and-java-enum#:~:text=75-,Hibernate%206,-Hibernate%206%20has) in stack overflow. Sometimes using direct SQL can be less frustrating and using Hexagonal Architecture it wouldn't be too hard to lift and shift to a new database if needed
- Breaking changes in ORM packages + vulnerability fixes - if we don't keep up to date with this and we are on say v4 and the newest version is v6 and a vulnerability is presented it may become a nightmare to upgrade to the new version (myself and a senior engineer once had this issue).


## Running the project locally
Prerequisites
- **Java JDK 23**
- **Docker:**  
  Needed to containerize and run the project.


### Running with Docker

From the project root, simply run:

```bash
./scripts/build-and-run-w-docker.sh
```

From here it will build and run the server on your local machine and a migration script will run which contains seed data so you can use the postman collection to visualise the requests and responses.

## API Endpoints

### GET /api/v1/recipes/trending

**Response Example:**

```json
[
    {
        "id": 10,
        "title": "Muffin",
        "imageUrl": "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg",
        "difficulty": "EASY"
    },
    {
        "id": 3,
        "title": "Waffles",
        "imageUrl": "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg",
        "difficulty": "HARD"
    }
]
```

### GET /api/v1/recipes/trending?difficulty=easy

```json
[
    {
        "id": 10,
        "title": "Muffin",
        "imageUrl": "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg",
        "difficulty": "EASY"
    },
    {
        "id": 4,
        "title": "French Toast",
        "imageUrl": "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg",
        "difficulty": "EASY"
    },
    {
        "id": 7,
        "title": "Fruit Salad",
        "imageUrl": "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg",
        "difficulty": "EASY"
    },
    {
        "id": 1,
        "title": "Pancakes",
        "imageUrl": "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg",
        "difficulty": "EASY"
    }
]
```

**_Note: the priority logic is all done in the repository layer as we order by ascending order based on position_**

```
@Query("SELECT r FROM RecipeEntity r JOIN TrendingRecipeEntity tr ON r.id = tr.recipeId WHERE r.difficulty = :difficulty ORDER BY tr.position ASC")
```

## Design Patterns Used

### Hex architecture
By setting the project up using hex architecture we have defined clear boundaries between the `domain` `api` `data` and `application`
This allows for a clear division between the system and makes it easier to maintain

Decoupling components also allows you to swap out or scale parts of the system independently, for example we may find out we want to swap from Postgres to DynamoDB, doing this will have no effect on the domain or API layers

Changes in one area also will not leak into the other boundaries and effect them which makes it easier to maintain

Testability: I always try and follow a TDD approach and hex arch allows for straight forward unit testing without needing to implement the full flow - for example I started on the domain and made sure the Query handler was set up and communicating with an in memory DB and then iterated from there.

### CQRS (without the commands at the moment)
**Command Query Responsibility Segregation** has been (semi) implemented - obviously we have no writes in this project and only reads but what this pattern allows for is to hook up two different data models for reads and writes
- Reads (Query) will read from read replicas
- Writes (Commands) will perform the CRUD operations on the master db

Let's say we are to add a new endpoint to upload a recipe - these are the changes that would be made:
- Add two datasources, one for reading, one for writing
- Implement a command handler and add the contract (interface) that the write repository must adhere to for example `createRecipe()`
- Rest layer maps the request to a command that is passed to the domain
- Domain maps the command to the correct respository action (in this case `createRecipe()`)
- The write repository will then perform the update on the Master db

As ReciMe will be a read heavy application, by separating reads from writes will improve system performance overall.
