<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
	<input id="searchInput" value="Type To Filter"/>
    <table border="1">
	<tr bgcolor="#9acd32">
		<th>Title</th>
		<th>Link</th>
		<th>Publication date</th>
	</tr>
	<xsl:for-each select="//item">
	<tr>
		<td>
			<xsl:value-of select="./title"/>
		</td>
		<td>
			<a>
				<xsl:attribute name="href">
					<xsl:value-of select="./link"/>
				</xsl:attribute>
				Link to article
			</a>
		</td>
		<td>
			<xsl:value-of select="./pubDate"/>
		</td>
	</tr>
	</xsl:for-each>
    </table>
	<script src="jquery-1.4.1.min.js"></script>
	<script>
	 $(document).ready(function() {

		  $("#searchInput").keyup(function(){
		//hide all the rows
			  $("table").find("tr").hide();

		//split the current value of searchInput
			  var data = this.value.split(" ");
		//create a jquery object of the rows
			  var jo = $("table").find("tr");
			  
		//Recusively filter the jquery object to get results.
			  $.each(data, function(i, v){
				  jo = jo.filter("*:contains('"+v+"')");
			  });
			//show the rows that match.
			  jo.show();
		 //Removes the placeholder text  
	   
		  }).focus(function(){
			  this.value="";
			  $(this).css({"color":"black"});
			  $(this).unbind('focus');
		  }).css({"color":"#C0C0C0"});

	  });

	</script>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>