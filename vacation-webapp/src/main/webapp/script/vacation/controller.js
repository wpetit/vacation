// Create a controller with name VacationsCtrl to bind to the html page.
app.controller('VacationsCtrl', function($scope, vacationsService) {
	// Refresh the grid, calling the appropriate service method.
	$scope.refreshGrid = function(page) {
		vacationsService
				.getAll(($scope.pagingOptions.pageSize * (page - 1)), 5)
				.success(function(data) {
					$scope.modelList = data.modelList;
					$scope.nbResults = data.total;
				});
	};

	$scope.pagingOptions = {
		pageSize : 5,
		currentPage : 1
	}

	$scope.gridOptions = {
		data : 'modelList',
		enablePaging : true,
		pagingOptions : $scope.pagingOptions
	};

	$scope.refreshGrid(1);
});

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
