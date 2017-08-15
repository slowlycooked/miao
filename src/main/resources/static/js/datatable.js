$(document).ready( function () {
	 var table = $('#recordTable').DataTable({
			"sAjaxSource": "/api/records",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
                  { "mData": "updated" },
				  { "mData": "type" },
				  { "mData": "amount" },
			]
	 })
});