// Service that provides vacations type operations
app.service('vacationTypeService', function($http) {
	// Save the vacation type given.
	this.saveVacationType = function(name, beginDate, endDate, numberOfDays) {
		return $http.post('rest/vacation/type', {
			type : name,
			beginDate : beginDate,
			endDate : endDate,
			numberOfDays : numberOfDays
		});
	};

	// Get all vacation types with sorting options.
	this.getVacationTypeList = function(sortAttribute, sortType) {
		return $http.get('rest/vacation/type', {
			params : {
				sortAttribute : sortAttribute,
				sortType : sortType
			}
		});
	};

	// Get vacation type with id given.
	this.getVacationType = function(vacationTypeId) {
		return $http.get('rest/vacation/type/' + vacationTypeId, {});
	};

	this.updateVacationType = function(id, name, beginDate, endDate,
			numberOfDays) {
		return $http.post('rest/vacation/type/' + id, {
			id : id,
			type : name,
			beginDate : beginDate,
			endDate : endDate,
			numberOfDays : numberOfDays
		});
	};
	
	this.deleteVacationType = function(id) {
		return $http.delete('rest/vacation/type/' + id);
	};
});
