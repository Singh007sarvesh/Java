import MySQLdb


conn = MySQLdb.connect("localhost","root","","test" )

	


def close_database():
    
    conn.commit()
    conn.close()