// Service that provides authentication operations
app.service('authenticationService', function($http) {
	this.authenticate = function(login, password) {
		// var xsrf = $.param({username: login, password : password});
		// return $http({
		// method: 'POST',
		// url: 'rest/authentication',
		// data: xsrf,
		// headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		// });
		return $http.post('rest/authentication', $.param({
			username : login,
			password : password
		}), {
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		});
	}
});
