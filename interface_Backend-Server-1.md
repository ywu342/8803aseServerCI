# interface Server-Backend

# User 
-------

## User Object

| Name | Type | Description |
| --- | --- | --- |
| id | String |  Unique identifier for a user |
| name | String |  Name of the user |
| password | String | Password of the user account |
| email | String | Email of the user |

## User REST API

| HTTP Method | URL | Input | Output | Description |
| --- | --- | --- | --- | --- |
| GET | /api/users/{userid} |  | User Object | Get the user with a particular user id (restricted access)  |
| POST | /api/users  | User name, password, email | User id | Create a new user |
| PUT | /api/users/{userid} | User Object | User Object |  Update an existing user |
| GET | /api/users/{userid}/member/playlist |  | List of Playlist objects |  Get a list of playlists associated with the user|
| GET | /api/users/{userid}/owner/playlist |  | List of Playlist objects |  Get a list of playlists associated with the owner|

# Songs 
-------

## Song Object

| Name | Type | Description |
| --- | --- | --- |
| id | String |  Unique identifier for a song |
| title | String |  Name of the song |
| artist | String | The Artist that composed this song |
| genre | String | Genre this song belongs to |
| url | String | Link to the song |

## Song REST API

|  | URL | Input | Output | Description |
| --- | --- | --- | --- | --- |
| GET | /api/songs/{songid} |  | Song Object |  Get the song with a particular song id |
| GET | /api/songs/{song_name} |  | List (Song Object) | Get the list of songs with the given name |
| GET | /api/songs/{genre_name} |  | List (Song Object) | Get the list of songs with genre name |
| GET | /api/songs/{artist_name}  |  | List (Song Object) | Get the list of songs with given artist name |

# Playlist
-------

## Playlist Object

| Name | Type | Description |
| --- | --- | --- |
| id | String |  Unique identifier for a playlist |
| name | String |  Name of the playlist |
| owner | User id | Owner of the playlist  |

## Playlist REST API

| HTTP Method | URL | Input | Output | Description |
| --- | --- | --- | --- | --- |
| POST | /api/playlist | Playlist name, Playlist owner | Playlist id | Create a new playlist |
| PUT | /api/playlist/{playlist id} | Playlist Object | Playlist Object | Edit an existing playlist |
| DELETE | /api/playlist/{playlist id}  | | | Delete an existing playlist |
| GET | /api/playlist/{playlist_id}/song |  | List (Song Objects) | Get all the songs of a particular playlist  |
| GET | /api/playlist/{playlist_id}/user |  | List (User object) | Get all the users of a particular playlist  |
| POST | /api/playlist/{playlist_id}/song | Song id | | Create a new song under playlist |
| POST | /api/playlist/{playlist_id}/user | User id | | Create a user under playlist |
| PUT | /api/playlist/{playlist id}/song/{song_id} | Int |  | Sending upvote/unvote for songs |
| DELETE | /api/playlist/{playlist id}/song/{song_id} |  |   | Remove a song from the playlist |
| DELETE | /api/playlist/{playlist id}/users/{userid} | | | Delete a user from a playlist | 
| GET | /api/playlist/{playlist id}/song/next_song | | Song object | Returns the next unplayed song to be played based on highest number of votes| 
| PUT | /api/playlist/{playlist id}/song/{song_id}/played | bool | | Marks a song as played |

