# Reddit Clone - Spring Boot Project

## Project Description

This project is a Reddit clone built using Spring Boot. The application includes basic functionalities such as user registration, login, post creation, commenting, voting, and managing subreddits. JWT is used for authentication along with basic security mechanisms.

## Features

- **Users**: Registration, login, token refresh, and logout.
- **Posts**: Create posts, view posts, view posts by subreddit, and view posts by user.
- **Comments**: Add comments to posts, view comments by posts, and view comments by user.
- **Subreddits**: Create and view subreddits.
- **Voting**: Vote on posts.

## Technologies

- **Backend**: Spring Boot, Spring Security, JWT
- **Database**: MySQL in docker
- **Dependency Management**: Maven
- **Object Mapping**: Mapstruct
- **Lombok**: To reduce boilerplate code

### Models

- `User`: Represents the application's users.
- `Comment`: Represents comments added to posts.
- `Post`: Represents posts created by users.
- `RefreshToken`: Handles JWT refresh tokens.
- `Subreddit`: Represents subreddits where users can add posts.
- `Vote`: Represents votes cast on posts.

### DTOs

- `CommentDto`
- `PostDto`
- `SubredditDto`
- `VoteDto`

### Services

- `AppUserService`: Manages users.
- `AuthService`: Handles user authentication.
- `CommentService`: Manages comments.
- `PostService`: Manages posts.
- `SubredditService`: Manages subreddits.
- `TokenService`: Handles JWT tokens.
- `VoteService`: Manages votes.

### Mappers

- `PostMapper`: Maps between `Post` entity and `PostDto`.
- `SubredditMapper`: Maps between `Subreddit` entity and `SubredditDto`.
- `VoteMapper`: Maps between `Vote` entity and `VoteDto`.

### Controllers

- **AuthController**: Manages authentication and session handling.
  - `POST /api/auth/signUp`
  - `POST /api/auth/login`
  - `POST /api/auth/refresh/token`
  - `POST /api/auth/logout`

- **CommentsController**: Manages comments.
  - `POST /api/comment/create`
  - `GET /api/comment/by-post/get/{id}`
  - `GET /api/comment/by-user/get/{id}`

- **PostController**: Manages posts.
  - `POST /api/post/created`
  - `GET /api/post/get/{id}`
  - `GET /api/post/getAll`
  - `GET /api/post/by-subreddit/get/{id}`
  - `GET /api/post/by-user/get/{id}`

- **SubredditController**: Manages subreddits.
  - `POST /api/subreddit/create`
  - `GET /api/subreddit/getAll`
  - `GET /api/subreddit/{id}`

- **VoteController**: Manages votes.
  - `POST /api/votes/vote`

## Getting Started

1. Clone the repository:
    ```bash
    git clone https://github.com/dominikkorwek/Reddit-clone-Spring-project.git
    ```
2. Configure the database in `application.properties`.
3. Run the application using Maven or Gradle.
4. The application will be available at `http://localhost:8080`.

## Future Enhancements

- Add unit and integration tests.
- Expand search and filtering functionality for posts.
- Implement a notification system.

## Author

Dominik Korwek
