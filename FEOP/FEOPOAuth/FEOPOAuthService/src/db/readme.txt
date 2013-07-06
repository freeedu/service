 Authorization Code Grant
 	request: http://localhost:8888/oauth2/oauth/authorize?response_type=code&client_id=authorization_code&redirect_uri=http%3A%2F%2Flocalhost%3A8888%2Foauth2%2F
 	response:	http://localhost:8888/oauth2/?code=YQKoBU
 	
 	request token: http://localhost:8888/oauth2/oauth/token?grant_type=authorization_code&code=qJjma4&redirect_uri=http%3A%2F%2Flocalhost%3A8888%2Foauth2%2F
 	response : {"access_token":"f42b43ef-d4f6-44d3-95d7-0554700d5c0d","token_type":"bearer","expires_in":59,"scope":"read write"}
 	
 	**** need to enter the client id and client secret ****
 	
 	
#########################################################################################################

Implicit Grant
	
	request:	http://localhost:8888/oauth2/oauth/authorize?response_type=token&client_id=implicit&redirect_uri=http%3A%2F%2Flocalhost%3A8888%2Foauth2%2F
	
	response:	http://localhost:8888/oauth2/#access_token=a26e5525-8169-4575-9462-61cce67e74e6&token_type=bearer&expires_in=59&scope=read%20write



#########################################################################################################

Resource Owner Password Credentials Grant
	
	request:	http://localhost:8888/oauth2/oauth/token?grant_type=password&username=mason.mei%40gmail.com&password=Stxaivj1986&client_id=resource_owner_password&client_secret=887c8e88167b281c

XXXXXXXXX

#########################################################################################################

Client Credentials Grant
	
	request:	http://localhost:8888/oauth2/oauth/token?grant_type=client_credentials&client_id=client_credentials&client_secret=a0c6ee5f31de21e6
	
	grant_type=client_credentials&
    client_id=CLIENT_ID&
    client_secret=CLIENT_SECRET
XXXXXXXXX

#########################################################################################################
Refresh Token Grant
	
	request:	http://localhost:8888/oauth2/oauth/authorize?grant_type=refresh_token&refresh_token=f42b43ef-d4f6-44d3-95d7-0554700d5c0d
	
XXXXXXXXX


#########################################################################################################





#########################################################################################################