# Web Application to Web Server Interface
All objects will be represented in JSON format unless otherwise noted.
`Optional<>` implies that the field is not guaranteed to be included in the object returned from the web server or is not required for an overloaded function.
REST API calls are all to be executed in the context of the currently authenticated user when a `Token` is required.



## Account
The `Account` object represents an account used by a user of the the party playlist application.

### Account Object
|Name               |Type                     |Description                                                             |
|-------------------|-------------------------|------------------------------------------------------------------------|
|user token         |`Token`                  |Unique identifying token representing this user's session.              |
|username           |`String`                 |User name representing an account.                                      |
|error              |`Optional<String>`       |Error reason in case of authentication failure.                         |

### AccountAuth Object
|Name               |Type                     |Description                                                             |
|-------------------|-------------------------|------------------------------------------------------------------------|
|username           |`String`                 |User name representing an account.                                      |
|password           |`String`                 |Password for unlocking this account.                                    |
|email              |`Optional<String>`       |Email to use for two factor authentication when creating account.       |

### Account REST API
|HTTP Method|URL                    |Input        |Output         |Description                                         |
|-----------|-----------------------|-------------|---------------|----------------------------------------------------|
| POST      |/api/accounts/create   |`AccountAuth`|`Account`      |Get the current user.                               |
| POST      |/api/accounts/login    |`AccountAuth`|`Account`      |Get the user with the provided user ID.             |



## Playlist
The `Playlist` object represents the metadata about a collection of songs.  The `PlaylistCreate` object represents the request to initialize a playlist in the database.

### Playlist Object
|Name           |Type          |Description                                                                            |
|---------------|--------------|---------------------------------------------------------------------------------------|
|id             |`String`      |Unique identifier for the playlist.                                                    |
|name           |`String`      |User-friendly name for the playlist.                                                   |
|description    |`String`      |User-given description of the playlist.                                                |
|songs          |`List<String>`|List of song ID's associated with this playlist.                                       |

### PlaylistCreate Object
|Name           |Type          |Description                                                                            |
|---------------|--------------|---------------------------------------------------------------------------------------|
|name           |`String`      |User-friendly name for the playlist.                                                   |
|description    |`String`      |User-given description of the playlist.                                                |
|user token     |`String`      |Token representing the session of the user making this request.                        |

### PlaylistUpdate Object
|Name           |Type          |Description                                                                            |
|---------------|--------------|---------------------------------------------------------------------------------------|
|id             |`String`      |Playlist id of the playlist.                                                           |
|operation      |`String`      |Add or remove songs. Only two operations possible.                                     |
|song id        |`String`      |Id of the song being added or deleted.                                                 |

### PlaylistShare Object
|Name           |Type          |Description                                                                            |
|---------------|--------------|---------------------------------------------------------------------------------------|
|playlist id    |`String`      |Playlist id of the playlist.                                                           |
|recipient id   |`String`      |Id of the user with whom playlist is meant to be shared.                               |

### LeavePlaylist Object
|Name           |Type          |Description                                                                            |
|---------------|--------------|---------------------------------------------------------------------------------------|
|playlist id    |`String`      |Playlist id of the playlist.                                                           |
|user id        |`String`      |Id of the user who wants to leave the playlist.                                        |


### Playlist REST API
|HTTP Method|URL                   |Input            |Output         |Description                                      |
|-----------|----------------------|-----------------|---------------|-------------------------------------------------|
|POST       |/api/playlist/create  |`PlaylistCreate` |`String`       |Create a playlist and get the generated ID.      |
|POST       |/api/playlist/share   |`PlaylistShare`  |`Success/Error`|Share the playlist with another user.            |
|POST       |/api/playlist/update/ |`PlaylistUpdate` |`Success/Error`|Update the playlist.                             |
|POST       |/api/playlist/leave/  |`LeavePlaylist`  |`Success/Error`|Leave the playlist.                             |
|GET        |/api/playlist/get/{playlistId} |`Token` |`Playlist`     |Request detailed information about a playlist    |
|GET        |/api/playlist/get    |`Token`           |`List<String>` |Get all playlist ID's associated with this user. |



## Song
The `Song` object represents a single song's audio data.

### Song Object
|Name           |Type           |Description                                                                           |
|---------------|---------------|--------------------------------------------------------------------------------------|
|id             |`String`       |Unique identifier for this song.                                                      |
|name           |`String`       |Name of this song.                                                                    |
|artist         |`String`       |Artist of this song.                                                                  |
|audio_url      |`URL`          |Audio URL.                                                                            |

### Song REST API

|HTTP Method|URL                            |Input   |Output        |Description                                       |
|-----------|-------------------------------|--------|--------------|--------------------------------------------------|
|GET        |/api/songs/play/{songId}       |`String`|`Song`        |Given user token, return song data to play.       |
|GET        |/api/songs/search/{query}      |        |`List<String>`|Get all songs related to the query.               |

