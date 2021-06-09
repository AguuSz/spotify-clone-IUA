# User

- findOne(int id)                                               : User
- findOneByEmail(String email)                                  : User
- findByName(String name)                                       : User
- updateOne(int id, User user)                                  : User
- deleteOne(int id)                                             : User
- addFriend(int userId, int friendId)                           : User (friend)
- getFriendsList(int id)                                        : List<User>
- deleteFriend(int userId, int friendId)                        : User (friend)
- getFriendActivity(int friendId)                               : Activity

# Playlist

- findOne(int id)                                               : Playlist
- findByUserId(int userId)                                      : List<Playlist>
- findByName(String name)                                       : List<Playlist>
- saveOne(int userId, PlaylistDTO playlist)                     : Playlist
- deleteOne(int id)                                             : Playlist
- updateOne(Playlist playlist)                                  : Playlist
- insertContent (int idPlaylist, Content content)               : Content
- insertContent (int idPlaylist, List<Content> contentList)     : List<Content>
- deleteContent (int idPlaylist, int contentId)                 : Content

# Content

- findOne(int id)                                               : Content
- findByName(String name)                                       : List<Content>
- findByGenre(String genre)                                     : List<Content>
- findByArtist(String artist)                                   : List<Content>
- findByLanguage(String language)                               : List<Content>

# Album

- findOne(int id)                                               : Album
- findByName(String name)                                       : List<Album>
- findByArtist(String artist)                                   : List<Album>

# Artist

- findOne(int id)                                               : Artist
- findByName(String name)                                       : List<Artist>
- findByCountry(String country)                                 : Artist

# Genre

- findOne(int id)                                               : Genre
- findByName(String name)                                       : List<Genre>

# Device

- findOne(int id)                                               : Device
- findByName(int id)                                            : List<Device>
- findByMacAddress(String macAddress)                           : Device                
- findByModel(String model)                                     : List<Device>    
- findByUserId(int userId)                                      : List<Device>    
- updateOne(int id, Device device)                              : Device            
- deleteOne(int id)                                             : Device
- createDevice(DeviceDTO device)                                : Device            

# Country

- findOne(int id)                                               : Country
- findByName(String name)                                       : List<Country>

Una vez completado todo esto, se pondra el tema de las recomendaciones