// Service that provides vacations operations
app.service('vacationsService', function($http) {
	// Makes the REST request to get the data to populate the grid.
	this.getAll = function(startIndex, pageSize) {
		return $http.get('rest/vacation/list', {
			params : {
				startIndex : startIndex,
				pageSize : pageSize
			}
		});
	}
});

