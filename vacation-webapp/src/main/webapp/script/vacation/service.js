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
	this.save = function(from, to, vacationTypeId) {
		return $http.post('rest/vacation/' + vacationTypeId, {
			from : from,
			to : to
		});
	};

	this.getNumberOfVacations = function(vacationTypeId) {
		return $http.get('rest/vacation/count?typeId=' + vacationTypeId, null);
	};
});