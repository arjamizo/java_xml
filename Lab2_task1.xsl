<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <h2>My Address Book</h2>
    <table border="1">
      <tr bgcolor="#9acd32">
        <th>Name</th>
        <th>Surname</th>
        <th>Phone</th>
        <th>Color</th>
      </tr>
      <xsl:for-each select="employees/employee">
        <tr>
			<td>
				<xsl:value-of select="name"/>
			</td>
          <td>		  
		  <xsl:for-each select="./surname">
			<xsl:value-of select="."/>
			<xsl:if test="position()!=last()">, </xsl:if>
		  </xsl:for-each>
		  </td>
			<td>
				<xsl:value-of select="phone"/>
			</td>
			<td>
				<xsl:attribute name="style">
				background-color:<xsl:value-of select="color"/>
				</xsl:attribute>
			</td>
        </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>