<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <h2>TV: <xsl:value-of select="TVSCHEDULE/@NAME"/></h2>
    <table border="1">
      <tr bgcolor="#9acd32">
        <th>Banner</th>
        <th>Date</th>
        <th>Hour, title</th>
      </tr>
      <xsl:for-each select="TVSCHEDULE/CHANNEL">
		<xsl:for-each select="//PROGRAMSLOT">
			<tr>
				<td>
					<xsl:value-of select="../../BANNER"/>
				</td>
				<td>
					<xsl:value-of select="../DATE"/>
				</td>
				<td>
					<xsl:value-of select="./TIME"/>
					<xsl:value-of select="./TITLE"/>
				</td>
			</tr>
		</xsl:for-each>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>