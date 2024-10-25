# Reddit Clone - Spring Boot Project

## Project Description

 This project is a Reddit clone built using Spring Boot. 
 The application includes basic functionalities such as user 
 registration, login, post creation, commenting, voting, and 
 managing subreddits. JWT is used for authentication along 
 with basic security mechanisms. Api uses mySql and phpmyadmin
 from `docker-compose.yml`.

## Features

 - **Users**: Registration, login, token refresh and get current user.
 - **Posts**: Create posts, view posts by subreddit and  by user.
 - **Comments**: Add comments to posts, view comments by posts and by user.
 - **Subreddits**: Create and view subreddits.
 - **Voting**: Vote on posts and track votes.
 - **Token**: generate JWT token and refresh token.

## Technologies

 - **Backend**: Spring Boot, Spring Security, JWT
 - **Database**: MySQL in docker
 - **Dependency Management**: Maven
 - **Object Mapping**: Mapstruct
 - **Lombok**: To reduce boilerplate code
 - **utilities**: guava and timeago.
  
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
 - `CommentMapper`: Maps between `Comment` and `CommentDto`.
 - `VoteMapper`: Maps between `Vote` entity and `VoteDto`.

### Controllers

 - **AuthController**: Manages authentication and session handling.
  - `POST /api/auth/signUp`: Register a new user.
  - `POST /api/auth/login`: Authentiactes the user.
  - `POST /api/auth/refresh/token`: Refreshes the JWT token.
  - `POST /api/auth/logout`: Logs out and invalidate the refresh token.

 - **CommentsController**: Manages comments.
  - `POST /api/comment/create`: Create a new comment.
  - `GET /api/comment/by-post/get/{id}`: Retrieve comments associated with specific post.
  - `GET /api/comment/by-user/get/{id}`: Retrieve comments made by a specific user.

 - **PostController**: Manages posts.
  - `POST /api/post/created`:  Create a new post.
  - `GET /api/post/get/{id}`: Retrieve a post by its ID.
  - `GET /api/post/getAll`: Retrieve all posts.
  - `GET /api/post/by-subreddit/get/{id}`: List posts under a specific subreddit.
  - `GET /api/post/by-user/get/{id}`: List posts created by a specific user.

 - **SubredditController**: Manages subreddits.
  - `POST /api/subreddit/create`: Create a new subreddit.
  - `GET /api/subreddit/getAll`: Retrieve a list of all subreddits.
  - `GET /api/subreddit/{id}`: Retrieve a subreddit by its ID.

 - **VoteController**: Manages votes.
  - `POST /api/votes/vote`: Casts a vote on a post.

## Getting Started

1. Clone the repository:
    ```bash
    git clone https://github.com/dominikkorwek/Reddit-clone-Spring-project.git
    ```
2. Launch `docker-compose.yml`.
3. Run the application using Maven or Gradle.
4. The application will be available at `http://localhost:8080`.

## Future Enhancements

- Add unit and integration tests.
- Expand search and filtering functionality for posts.
- Implement a notification system.

## Contributing

Contributions are welcome! If you'd like to add new features, improve existing code, or fix bugs, feel free to fork the repository and submit a pull request. Please ensure your code is well-documented and follows the project's coding standards.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

This project is maintained by **Dominik Korwek**. If you have any questions, suggestions, or feedback, feel free to reach out.

