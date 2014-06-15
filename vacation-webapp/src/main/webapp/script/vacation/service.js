// Service that provides vacations operations
app.service('vacationService', function($http) {
	this.getAll = function(startIndex, pageSize, sortAttribute, sortType) {
		return $http.get('rest/vacation/list', {
			params : {
				startIndex : startIndex,
				pageSize : pageSize,
				sortAttribute : sortAttribute,
				sortType : sortType
			}
		});
	};
});