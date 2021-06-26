package utils;

public class Paths {
    public static final String basePath = "localhost:8080/";

    public static class Artists {
        public static final String findOne =  "/artist/findOne/:artistId";
        public static final String findByName = "/artist/findByName/:name";
        public static final String findByCountry = "/artist/findByCountry/:country";
        public static final String getAll = "/artist/getAll";
    }

    public static class Auth {
        public static final String login = "/auth/login"; // JSON
        public static final String register = "/auth/register"; // JSON
    }

    public static class Users {
        public static final String findOne = "/user/findOne/:userId";
        public static final String findOneByEmail = "/user/findOneByEmail/:email";
        public static final String findByName = "/user/findByName/:name";
        public static final String update = "/user/update"; // JSON
        public static final String delete = "/user/delete/:userId";
        public static final String getActivity = "/user/getActivity/:userId";
    }

    public static class Playlists {
        public static final String create = "/playlist/create"; // JSON
        public static final String delete = "/playlist/delete/:playlistId";
        public static final String update = "/playlist/update"; // JSON
        public static final String insertContent = "/playlist/insert"; // JSON
        public static final String findOne = "/playlist/findOne/:playlistId";
        public static final String findByName = "/playlist/findByName/:name";
        public static final String findByUserId = "/playlist/findByUserId/:userId";
        public static final String getMostListenedGenreByUserId = "/playlist/getMostListenedGenreByUserId/:userId";
        public static final String getMostListenedSongsByUserId = "/playlist/getMostListenedSongsByUserId/:userId";
        public static final String getAll = "/playlist/getAll";
    }

}
