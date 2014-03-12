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
		<xsl:for-each select="//PROGRAMSLOT">
			<xsl:variable name="prvpos" select="position()-1"/>
			<xsl:variable name="thspos" select="position()"/>
			<xsl:variable name="pprv"><xsl:value-of select="../../../descendant::PROGRAMSLOT[$prvpos]/../../@CHAN" /></xsl:variable>
			<xsl:variable name="tthis"><xsl:value-of select="../../../descendant::PROGRAMSLOT[$thspos]/../../@CHAN" /></xsl:variable>
			
			<tr>
				<xsl:if test="position()=1 or $pprv!=$tthis">
				<td>
					<xsl:attribute name="rowspan">
						<xsl:value-of select="count(../../descendant::PROGRAMSLOT)"/>
					</xsl:attribute>
					<xsl:value-of select="../../BANNER"/>
				</td>
				</xsl:if>
				<xsl:if test="position()=1 or ./ancestor::DAY/DATE!=../../../descendant::PROGRAMSLOT[$prvpos]/../DATE">
				<!--http://www.w3schools.com/xpath/xpath_axes.asp-->
				<td>
					<xsl:attribute name="rowspan">
						<xsl:value-of select="count(../descendant::PROGRAMSLOT)"/>
					</xsl:attribute>
					<xsl:value-of select="../DATE"/>
				</td>
				</xsl:if>
				<td>
					<b><xsl:value-of select="./TIME"/></b>&#160;<xsl:value-of select="./TITLE"/>
				</td>
			</tr>
		</xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>