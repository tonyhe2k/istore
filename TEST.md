1. Create user

	1. Post
	2. http://localhost:8011/user-service/user
	3. Body
		{
			"username" :  "tonyhe1",
			"firstName": "Tony",
			"lastName":  "He",
			"password":  "password",
			"email" :    "tonyhe@xyz.com"
		
		}

2. login using newly created user

	1. POST
	2. http://localhost:8011/user-service/user/login
	3. Body
		{
			"password":  "password",
			"email" :    "tonyhe@xyz.com"
		
		}
		
	4. Copy from response
		a. userID
		b. JWT Token


3. Get User through API Gateway
	1. GET
	2. http://localhost:8011/user-service/user/ae0ec5ea-5c36-4403-84c9-15ca5cdce7be
	3. Headers
		a. Key=Authorization
		b. Value=Bearer JWT Token from above
