// Service that provides vacations operations.
app.service('vacationService', function($http) {
	// Get all vacations with paging and sorting options.
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
	
	this.get = function(id) {
		return $http.get('rest/vacation/'+id);
	};
	
	// Save the vacation given.
	this.save = function(from, to, vacationTypeId) {
		return $http.post('rest/vacation', {
			from : from,
			to : to,
			type : {
				id : vacationTypeId
			}
		});
	};

	// Update the vacation given.
	this.update = function(id, from, to, vacationTypeId) {
		return $http.post('rest/vacation/' + id, {
			id : id,
			from : from,
			to : to,
			type : {
				id : vacationTypeId
			}
		});
	};
	
	// Delete the vacation given.
	this.deleteVacation = function(id) {
		return $http.delete('rest/vacation/' + id);
	};

	this.getNumberOfVacations = function(vacationTypeId) {
		return $http.get('rest/vacation/count?typeId=' + vacationTypeId, null);
	};
});