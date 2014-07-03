// Service that provides vacations type operations
app.service('vacationTypeService', function($http) {
	// Makes the REST request to get the data to populate the grid.
	this.saveVacationType = function(name, beginDate, endDate, numberOfDays) {
		return $http.put('rest/vacation/type', {
			type : name,
			beginDate : beginDate,
			endDate : endDate,
			numberOfDays : numberOfDays
		});
	};

	this.getVacationTypeList = function(sortAttribute, sortType) {
		return $http.get('rest/vacation/type', {
			params : {
				sortAttribute : sortAttribute,
				sortType : sortType
			}
		});
	};
});
