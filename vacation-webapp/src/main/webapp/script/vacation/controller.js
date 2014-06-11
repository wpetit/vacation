// Create a controller with name VacationsCtrl to bind to the html page.
app.controller('VacationsCtrl', function($scope, vacationService) {
	// Refresh the grid, calling the appropriate service method.
	$scope.refreshGrid = function(page) {
		vacationService.getAll(($scope.pagingOptions.pageSize * (page - 1)), 5)
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

