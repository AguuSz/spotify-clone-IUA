# Retornar una playlist en base al genero mas escuchado los 15 dias pasados. Usuario -> id 1
SELECT 
	id_content, name, length, genre, language
FROM content
	INNER JOIN genre ON content.id_genre = genre.id_genre
    INNER JOIN language ON content.id_language = language.id_language
WHERE content.id_genre = (SELECT 
    g.id_genre
FROM listen l
	INNER JOIN content c ON l.id_content = c.id_content
    INNER JOIN genre g ON c.id_genre = g.id_genre
    INNER JOIN user u ON l.id_user = u.id_user
WHERE `date` >= DATE_SUB(NOW(), INTERVAL 15 DAY) AND u.id_user = 1
GROUP BY genre
ORDER BY COUNT(genre) DESC LIMIT 1);

# Obtener una playlist en base a los 3 artistas mas escuchados para el usuario -> id 1
SELECT 
    content.id_content,
    name,
    length,
    id_genre,
    id_language
FROM content
    INNER JOIN uploads ON uploads.id_content = content.id_content
WHERE id_artist IN (SELECT * FROM (SELECT 
    id_artist
FROM listen INNER JOIN user ON listen.id_user = user.id_user
INNER JOIN uploads ON listen.id_content = uploads.id_content
WHERE listen.id_user = 1
GROUP BY id_artist
ORDER BY COUNT(id_artist) DESC LIMIT 3) AS p);

# Obtener lista de amigos de usuario -> id 1
SELECT * 
FROM friends 
	INNER JOIN user ON user.id_user IN (friends.id_user1, friends.id_user2) 
    INNER JOIN country ON user.id_country = country.id_country 
WHERE 1 IN (friends.id_user1, friends.id_user2) AND NOT user.id_user = 1;

# Encontrar contenido ubicado en una playlist. En este caso playlist -> id 
SELECT * 
FROM content 
	INNER JOIN adds ON content.id_content = adds.id_content 
    INNER JOIN playlist ON adds.id_playlist = playlist.id_playlist 
    INNER JOIN genre ON content.id_genre = genre.id_genre 
    INNER JOIN language ON content.id_language = language.id_language 
WHERE playlist.id_playlist = 3;

# Cantidad de veces que se escucho a distintos artistas dentro de los pasados 30 dias. ID usuario -> 1
SELECT 
	stage_name AS `Artista`,
    COUNT(stage_name) AS `Veces que se escucho`
FROM listen 
	INNER JOIN user ON listen.id_user = user.id_user
	INNER JOIN uploads ON listen.id_content = uploads.id_content
    INNER JOIN artist ON artist.id_artist = uploads.id_artist
WHERE listen.id_user = 1 AND listen.date >= DATE_SUB(NOW(), INTERVAL 30 DAY)
GROUP BY stage_name;

