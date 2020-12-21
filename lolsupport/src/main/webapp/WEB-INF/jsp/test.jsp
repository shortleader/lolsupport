<html lang="en">
	<head>
		<meta charset="UTF-8">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	</head>
	<body>
		<div id="contents"></div>
		<script>
			$.ajax({
				type: "GET",
				url: "APITest",
				success: (data) => {
				console.log(data);
				$('#contents').html(data);
				}
			});
		</script>
	</body>
</html>