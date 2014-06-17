// Create a controller with name VacationsCtrl to bind to the html page.
vacationAppControllers.controller('VacationsCtrl', function($scope, vacationService) {
	// Refresh the grid, calling the appropriate service method.
	$scope.refresh = function() {
		var page = $scope.pagingOptions.currentPage;
		vacationService.getAll(($scope.pagingOptions.pageSize * (page - 1)), 5,
				$scope.sortOptions.field, $scope.sortOptions.direction)
				.success(function(data) {
					$scope.modelList = data.modelList;
					$scope.nbResults = data.total;
				});
	};

	$scope.pagingOptions = {
		pageSize : 5,
		currentPage : 1
	};

	// sort
	$scope.sortOptions = {
		field : "from" ,
		direction : "desc" 
	};
	
	$scope.sortBy = function(attribute) {
		if($scope.sortOptions.field != attribute) {
			$scope.sortOptions = {field : attribute, direction : "desc"};
		} else {
			if($scope.sortOptions.direction == "desc") {
				$scope.sortOptions.direction = "asc";
			} else {
				$scope.sortOptions.direction = "desc";
			}
		}
	}

	$scope.$watch('sortOptions', function(newVal, oldVal) {
		if (newVal !== oldVal) {
			$scope.refresh();
		}
	}, true);

	$scope.refresh();
});
