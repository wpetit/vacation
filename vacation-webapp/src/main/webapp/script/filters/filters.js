//sort filter
vacationAppFilters.filter('sortIconFilter', function() {
	return function(input, sortOptions) {
		var icon = "";
		if (sortOptions.field == input) {
			if ("desc" == sortOptions.direction) {
				icon = 'glyphicon glyphicon-sort-by-attributes-alt sortIcon';
			} else {
				icon = 'glyphicon glyphicon-sort-by-attributes sortIcon';
			}
		} else {
			icon = 'glyphicon glyphicon-sort sortIcon';
		}
		return icon;
	};
});

vacationAppFilters.filter('fromDateMorningAfternoon', function() {
	return function(timestamp) {
		var date = new Date(timestamp);
		var result = "";
		if (date.getHours() < 12) {
			result = "Morning";
		} else {
			result = "Afternoon";
		}
		return result;
	};
});

vacationAppFilters.filter('toDateMorningAfternoon', function() {
	return function(timestamp) {
		var date = new Date(timestamp);
		var result = "";
		if (date.getHours() < 13) {
			result = "Midday";
		} else {
			result = "Evening";
		}
		return result;
	};
});