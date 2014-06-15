// Create a controller with name VacationsCtrl to bind to the html page.
app.controller('VacationsCtrl', function($scope, vacationService) {
	// Refresh the grid, calling the appropriate service method.
	$scope.pageChanged = function() {
		var page = $scope.pagingOptions.currentPage;
		vacationService.getAll(($scope.pagingOptions.pageSize * (page - 1)), 5)
				.success(function(data) {
					$scope.modelList = data.modelList;
					$scope.nbResults = data.total;
				});
	};

	$scope.pagingOptions = {
		pageSize : 5,
		currentPage : 1
	};

	$scope.gridOptions = {
		data : 'modelList',
		enablePaging : true,
		pagingOptions : $scope.pagingOptions,
		columnDefs : [ {
			field : 'type.type',
			displayName : 'Type'
		}, {
			field : 'from',
			displayName : 'From',
			cellFilter : 'date:"dd-MM-yyyy HH:mm"'
		}, {
			field : 'to',
			displayName : 'To',
			cellFilter : 'date:"dd-MM-yyyy HH:mm"'
		} ]
	};

	$scope.pageChanged();
});
