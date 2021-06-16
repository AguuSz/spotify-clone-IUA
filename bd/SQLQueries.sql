# 1 - Retornar canciones del genero mas escuchado por el usuario de id 1 los 15 dias pasados
# [id, nombre, duracion, genero y lenguaje]
SELECT 
    content.id_content, content.name, length, genre, language
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
GROUP BY content.id_content, content.name, length, genre, language
ORDER BY COUNT(genre) DESC LIMIT 1);

# 2 - Obtener las canciones de los 3 artistas mas escuchados
# [id, nombre, duracion, genero y lenguaje]
SELECT 
    content.id_content, name, length, id_genre, id_language, stage_name
FROM content
    INNER JOIN uploads ON uploads.id_content = content.id_content
    INNER JOIN artist ON artist.id_artist = uploads.id_artist
WHERE artist.id_artist IN (SELECT * FROM (SELECT 
    id_artist
FROM listen INNER JOIN user ON listen.id_user = user.id_user
INNER JOIN uploads ON uploads.id_content = listen.id_content
WHERE listen.id_user = 1
GROUP BY content.id_content, name, length, id_genre, id_language, stage_name
ORDER BY COUNT(id_artist) DESC LIMIT 3) AS p);

# 3 - Cantidad de veces que se escucho un genero agrupado por email y por genero dentro de todo el mes pasado
SELECT 
    email, genre, `date`, COUNT(genre) as `Veces escuchado`
FROM `spotify-clone`.listen l
    INNER JOIN content c ON l.id_content = c.id_content
    INNER JOIN genre g ON c.id_genre = g.id_genre
    INNER JOIN user u ON l.id_user = u.id_user
WHERE `date` >= DATE_ADD(LAST_DAY(DATE_SUB(NOW(), INTERVAL 2 MONTH)), INTERVAL 1 DAY) and `date` <= DATE_SUB(NOW(), INTERVAL 1 MONTH)
GROUP BY genre, email, `date`;

# 4 - Cantidad de veces que se escucho un genero agrupado por email y genero, pero mostrando todos los records
SELECT 
    email,
    genre,
    `date`,
    COUNT(genre) as `Veces escuchado`
FROM `spotify-clone`.listen l
    INNER JOIN content c ON l.id_content = c.id_content
    INNER JOIN genre g ON c.id_genre = g.id_genre
    INNER JOIN user u ON l.id_user = u.id_user
GROUP BY genre, email, `date`;

# 5 - Mostrar las 10 canciones mas escuchadas en orden descendente
SELECT listen.id_content, user.name, content.name AS cancion, COUNT(listen.id_content) AS cantidad FROM listen
    INNER JOIN user ON user.id_user = listen.id_user
    INNER JOIN content ON content.id_content = listen.id_content
WHERE listen.id_user = 1
GROUP BY listen.id_content, user.name, content.name
ORDER BY cantidad DESC LIMIT 10;

# 6 - Canciones de rock que el usuario 1 nunca escucho [id, nombre, duracion, genero, lenguaje]
SELECT c.id_content, c.name, c.length, g.genre, l.language
FROM content c INNER JOIN genre g ON c.id_genre = g.id_genre
    INNER JOIN language l ON c.id_language = l.id_language
WHERE c.id_content NOT IN (SELECT c.id_content
        FROM content c INNER JOIN listen l ON c.id_content = l.id_content)
        AND c.id_genre = 1;
# 7 - Artistas que subieron al menos 4 canciones en espaÃ±ol
# [id, nombre, pais]
SELECT a.id_artist, a.stage_name, co.name
FROM artist a INNER JOIN uploads u ON a.id_artist = u.id_artist
    INNER JOIN country co ON a.id_country = co.id_country
    INNER JOIN content c ON u.id_content = c.id_content
WHERE c.id_language = 1
GROUP BY a.id_artist, a.stage_name, co.name
HAVING COUNT(a.id_artist) > 4;

# 8 - Mostrar que cantidad de contenido hay de un determinado idioma en las playlists del usuario
SELECT
    playlist.name AS `Nombre playlist`,
    COUNT(playlist.name) AS `Canciones en spanish (1)`
FROM playlist 
    INNER JOIN adds ON playlist.id_playlist = adds.id_playlist
    INNER JOIN content ON adds.id_content = content.id_content
WHERE content.id_language = 1 AND playlist.id_user = 1
GROUP BY playlist.name;

# 9 - Cantidad de veces que se agregaron las canciones a una playlist
# [id, nombre, cantidad de veces que se agrego]
SELECT c.id_content, c.name, COUNT(a.id_playlist) cantidad
FROM content c LEFT JOIN adds a ON c.id_content = a.id_content
GROUP BY c.id_content, c.name
ORDER BY cantidad DESC;

# 10 - Mostrar los albumes sacados en los ultimos 4 meses cuyo artista contiene una "D" en su nombre. [Fecha, Artista, Album]
SELECT 
    date as Fecha, 
    stage_name as Artista,
    album.name as Album
FROM uploads 
    INNER JOIN album ON uploads.id_album = album.id_album
    INNER JOIN artist ON artist.id_artist = uploads.id_artist
WHERE `date` >= DATE_SUB(NOW(), INTERVAL 4 MONTH) AND stage_name LIKE '%D%'
GROUP BY date, stage_name, album.name;