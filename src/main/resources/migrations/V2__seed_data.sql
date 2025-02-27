-- NOTE: THIS IS TEST SEED DATA THIS WILL NOT BE DONE IN A PRODUCTION ENVIRONMENT

INSERT INTO recipe (title, image_url, difficulty) VALUES
('Pancakes', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 'EASY'),
('Omelette', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 'MEDIUM'),
('Waffles', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 'HARD'),
('French Toast', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 'EASY'),
('Scrambled Eggs', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 'MEDIUM'),
('Bagel with Cream Cheese', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 'HARD'),
('Fruit Salad', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 'EASY'),
('Granola', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 'MEDIUM'),
('Smoothie', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 'HARD'),
('Muffin', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 'EASY');

INSERT INTO trending_recipe (recipe_id, position) VALUES
(10, 1),
(3, 2),
(2, 3),
(4, 4),
(7, 5),
(6, 6),
(5, 7),
(9, 8),
(8, 9),
(1, 10);
