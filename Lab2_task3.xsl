<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<!-- 
This file operatores on Lab1_task1.xml
-->

<xsl:template match="/">
<html>
<head><style>
*{margin:0px}
body{font-family:Arial;}
table{width:100%;}
tr{margin-bottom:0px;}
button{display:inline;float:left;}
h1 {color: green;}
h1>span {color: gold;}
</style></head>
<body>
<div style="margin:0px auto; width:400px;">
<h1>&#x1f4d6; Corporate address book <span>PRO</span></h1>

		<xsl:for-each select="//employee">
		<h2>Pracownik <xsl:value-of select="name"/><span>&#160;</span><xsl:value-of select="surname"/></h2>
		<table>
			<tr>
				<td>Imię</td> <td> <xsl:value-of select="name"/></td>
			</tr>
			<tr>
				<td>Nazwisko</td><td><xsl:value-of select="surname"/></td>
			</tr>
			<tr>
				<td>Telefon</td>
				<td>
					<xsl:value-of select="phone"/>
					<xsl:if test="phone/@operator"> (<xsl:value-of select="phone/@operator"/>)</xsl:if>
				</td>
			</tr>
			<xsl:for-each select="address">
				<tr>
				<td>Adres</td>
				<td>
				<xsl:value-of select="./street"/> &#160;
				<xsl:value-of select="./city"/> &#160;
				<xsl:value-of select="./country"/></td>
				</tr>
					<xsl:if test="gps">
					<tr>
						<td>GPS</td><td>
						<xsl:value-of select="gps"/>
						</td>
					</tr>
					</xsl:if>
			</xsl:for-each>
			<tr>
			<td colspan="2"><button disabled="disabled">Send e-mail</button> <button disabled="disabled">Add to friends</button></td>
			</tr>
		</table>
		</xsl:for-each>
</div>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="http://heydonworks.com/practical_aria_examples/js/a11y.js"></script>
<script>
$('h2').unbind('click').click(
function(e){var e=$(e.target).next();
e.toggle(1000);
		});
</script>
</body>
</html>
</xsl:template>

</xsl:stylesheet>