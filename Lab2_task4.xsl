<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <head><title>Mail to <xsl:value-of select="requests/author/name"/></title></head>
  <body>
  
      <xsl:for-each select="requests/author">
	  <div>
  <address style="text-align:left;clear:left;">Od: AZ, PWR, 71AREA GPS:51,17</address>
  <address style="text-align:right;clear:right;">Do: <xsl:value-of select="./name"/></address>
  <p>Dear <xsl:value-of select="./name"/>,</p>
<p>Your paper under title <xsl:value-of select="requests/author/title"/> that was register under nr ID <xsl:value-of select="./id"/> was successfully uploaded to a DepCoS-RELCOMEX conference.</p>
<p>You can check that the file is correctly uploaded by following this link: 
			<a>
				<xsl:attribute name="href">
					http://RELCOMEX.com/conf.php?id=<xsl:value-of select="./link"/>
				</xsl:attribute>
				here</a>.</p>
<p>Thanks for submitting to DepCoS-RELCOMEX 2011. Until the deadline, you can still update your information or transmit a revised file.</p>
<p>Best regards,<br/>DepCoS-RELCOMEX 2011 chairs.</p>
	</div>
	
			<xsl:if test="position()!=last()"><hr/></xsl:if>
      </xsl:for-each>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>