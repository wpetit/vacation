// Service that provides vacations type operations
app.service('vacationTypeService', function($http) {
	// Makes the REST request to get the data to populate the grid.
	this.saveVacationType = function(name, beginDay, beginMonth, endDay, endMonth, numberOfDays) {
		return $http.post('rest/vacation/type', {
			name : name,
			beginDay : beginDay,
			beginMonth : beginMonth,
			endDay : endDay,
			endMonth : endMonth,
			numberOfDays : numberOfDays
		});
	}
});


