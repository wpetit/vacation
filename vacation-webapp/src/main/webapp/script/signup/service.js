// Service that provides vacations operations.
app.service('signUpService', function($http) {
	// Get all vacations with paging and sorting options.
	this.createUser = function(username, password) {
		return $http.post('rest/user/'+username, password);
	};
});