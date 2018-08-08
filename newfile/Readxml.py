import xml.etree.ElementTree as ET
import DatabaseConnection


cursor = DatabaseConnection.conn.cursor()
tree = ET.parse('employee2.xml')
root = tree.getroot()


# to insert data from xml file directly
for top_child in root:
		cursor.execute("insert into emp_records values(%s,%s,%s,%s,%s,%s,%s,%s)" % (top_child[0].text,top_child[1].text,top_child[2].text,top_child[3].text,top_child[4].text,top_child[5].text,top_child[6].text,top_child[7].text))
		print("successfully inserted")


DatabaseConnection.close_database()