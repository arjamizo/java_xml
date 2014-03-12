<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
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
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>