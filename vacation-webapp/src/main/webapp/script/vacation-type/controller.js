// Create a controller with name VacationsCtrl to bind to the html page.
vacationAppControllers.controller('VacationTypeCtrl', function($scope,
		$location, vacationService, vacationTypeService) {

	// initialization
	$scope.beginDate = new Date();
	$scope.endDate = new Date();
	$scope.dateFormat = 'dd/MM/yyyy';
	$scope.numberOfDays = 1;
	// sort
	$scope.sortOptions = {
		field : "type",
		direction : "asc"
	};

	$scope.openBeginDatePicker = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.beginDatePickerOpened = true;
	};

	$scope.openEndDatePicker = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.endDatePickerOpened = true;
	};

	// Save the vacation type.
	$scope.save = function() {
		vacationTypeService.saveVacationType($scope.typeName, $scope.beginDate,
				$scope.endDate, $scope.numberOfDays).success(function(data) {
			$scope.typeName = null;
			$scope.beginDate = new Date();
			$scope.endDate = new Date();
			$scope.numberOfDays = 1;
		});
	};

	// Cancel the current action. Go back to list.
	$scope.cancel = function() {
		$location.path('/vacationTypes/list');
	};

	// Get all vacation types with sort.
	$scope.getAll = function() {
		vacationTypeService.getVacationTypeList($scope.sortOptions.field,
				$scope.sortOptions.direction).success(function(data) {
			$scope.vacationTypes = data;
		});
	};

	// Sort vacation types.
	$scope.sortBy = function(attribute) {
		if ($scope.sortOptions.field != attribute) {
			$scope.sortOptions = {
				field : attribute,
				direction : "asc"
			};
		} else {
			if ($scope.sortOptions.direction == "desc") {
				$scope.sortOptions.direction = "asc";
			} else {
				$scope.sortOptions.direction = "desc";
			}
		}
	};

	// Refresh vacation types when sort options change.
	$scope.$watch('sortOptions', function(newVal, oldVal) {
		if (newVal !== oldVal) {
			$scope.getAll();
		}
	}, true);

	// Refresh vacation types balances when vacation types changes.
	$scope.$watch('vacationTypes', function(newVal, oldVal) {
		if (newVal !== oldVal) {
			$scope.getVacationTypesBalances();
		}
	}, true);

	// Go to create vacation type screen.
	$scope.goToCreateVacationType = function() {
		$location.path('/vacationTypes/create');
	};

	// Get vacation types balances.
	$scope.getVacationTypesBalances = function() {
		$scope.numberOfVacations = new Array();
		$scope.vacationTypes.forEach(function(vacationType) {
			$scope.retrieveVacationCount(vacationType.id, vacationType.type,
					vacationType.numberOfDays);
		});
	};

	// Get vacation type balance for the given vacation type.
	$scope.retrieveVacationCount = function(vacationTypeId, vacationTypeName,
			max) {
		vacationService.getNumberOfVacations(vacationTypeId).success(
				function(data) {
					$scope.numberOfVacations.push({
						vacationTypeId : vacationTypeId,
						vacationTypeName : vacationTypeName,
						max : max,
						count : data
					});
				});
	};

	// Initialization : get all vacation types.
	$scope.getAll();
});
