import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
/* <applet code="sales" width=1000 height=650>
</applet> */
public class sales extends Applet implements ActionListener
{
	Image img;
	String query;
	Button ext,tran,load,dat,que,del,q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13;
	String msg = "";
	CardLayout cl=new CardLayout();
	JPanel jp1,jp2;
	JTable jt;
	public void init() 
	{
		img=getImage(getCodeBase(),"a.jpg");
		jp1=new JPanel();
		jp2=new JPanel();
		jp1.setLayout(null);
		jp2.setLayout(null);
		this.setName("ETL Tools");
		this.setLayout(cl);
		Font myFont = new Font("Arial",Font.PLAIN,17);
		ext= new Button("Input Data");
		tran= new Button("Extract");
		jp1.add(ext);
		ext.setBackground(Color.darkGray.darker());
		ext.setForeground(Color.white.brighter());
		ext.setBounds(820,100,100,40);
		ext.addActionListener(this);  
    	jp1.add(tran);
    	tran.setBackground(Color.darkGray.darker());
    	tran.setForeground(Color.white.brighter());
    	tran.setBounds(820,170,100,40);
    	tran.addActionListener(this);   
    	load= new Button("Transform");
		jp1.add(load);
		load.setBackground(Color.darkGray.darker());
		load.setForeground(Color.white.brighter());
    	load.setBounds(820,240,100,40);
    	load.addActionListener(this);
    	ext.setFont(myFont);
    	load.setFont(myFont);
    	tran.setFont(myFont);
    	dat= new Button("Load");
		jp1.add(dat);
		dat.setBackground(Color.darkGray.darker());
		dat.setForeground(Color.white.brighter());
    	dat.setBounds(820,310,100,40);
    	dat.addActionListener(this);
    	dat.setFont(myFont);
    	que= new Button("Queries");
		jp1.add(que);
		que.setBackground(Color.darkGray.darker());
		que.setForeground(Color.white.brighter());
    	que.setBounds(820,380,100,40);
    	que.addActionListener(this);
    	que.setFont(myFont);
    	del= new Button("Delete");
		jp1.add(del);
		del.setBackground(Color.red.brighter());
		del.setForeground(Color.white.brighter());
    	del.setBounds(820,450,100,40);
    	del.addActionListener(this);
    	del.setFont(myFont);
    	this.add(jp1);
    	q1= new Button("Total Sales");
		jp2.add(q1);
		q1.setBackground(Color.darkGray.darker());
		q1.setForeground(Color.white.brighter());
    	q1.setBounds(120,100,120,40);
    	q1.addActionListener(this);
    	q1.setFont(myFont);
    	q2= new Button("Customer Details");
		jp2.add(q2);
		q2.setBackground(Color.darkGray.darker());
		q2.setForeground(Color.white.brighter());
    	q2.setBounds(325,250,130,40);
    	q2.addActionListener(this);
    	q2.setFont(myFont);
    	q6= new Button("< Back");
		jp2.add(q6);
		q6.setBackground(Color.darkGray.darker());
		q6.setForeground(Color.white.brighter());
    	q6.setBounds(750,550,120,40);
    	q6.addActionListener(this);
    	q6.setFont(myFont);
    	q3= new Button("Net Profit");
		jp2.add(q3);
		q3.setBackground(Color.darkGray.darker());
		q3.setForeground(Color.white.brighter());
    	q3.setBounds(330,100,120,40);
    	q3.addActionListener(this);
    	q3.setFont(myFont);
    	q4= new Button("Top Customers");
		jp2.add(q4);
		q4.setBackground(Color.darkGray.darker());
		q4.setForeground(Color.white.brighter());
    	q4.setBounds(540,100,120,40);
    	q4.addActionListener(this);
    	q4.setFont(myFont);
    	q13= new Button("Sales by Month");
		jp2.add(q13);
		q13.setBackground(Color.darkGray.darker());
		q13.setForeground(Color.white.brighter());
    	q13.setBounds(750,100,120,40);
    	q13.addActionListener(this);
    	q13.setFont(myFont);
    	q5= new Button("Sales by Country");
		jp2.add(q5);
		q5.setBackground(Color.darkGray.darker());
		q5.setForeground(Color.white.brighter());
    	q5.setBounds(120,250,130,40);
    	q5.addActionListener(this);
    	q5.setFont(myFont);
    	q7= new Button("Store Details");
		jp2.add(q7);
		q7.setBackground(Color.darkGray.darker());
		q7.setForeground(Color.white.brighter());
    	q7.setBounds(540,250,120,40);
    	q7.addActionListener(this);
    	q7.setFont(myFont);
    	q8= new Button("Product Details");
		jp2.add(q8);
		q8.setBackground(Color.darkGray.darker());
		q8.setForeground(Color.white.brighter());
    	q8.setBounds(750,250,120,40);
    	q8.addActionListener(this);
    	q8.setFont(myFont);
    	q9= new Button("S.Person Details");
		jp2.add(q9);
		q9.setBackground(Color.darkGray.darker());
		q9.setForeground(Color.white.brighter());
    	q9.setBounds(120,400,130,40);
    	q9.addActionListener(this);
    	q9.setFont(myFont);
    	q10= new Button("Sales by Store");
		jp2.add(q10);
		q10.setBackground(Color.darkGray.darker());
		q10.setForeground(Color.white.brighter());
    	q10.setBounds(330,400,130,40);
    	q10.addActionListener(this);
    	q10.setFont(myFont);
    	q11= new Button("All Transactions");
		jp2.add(q11);
		q11.setBackground(Color.darkGray.darker());
		q11.setForeground(Color.white.brighter());
    	q11.setBounds(540,400,130,40);
    	q11.addActionListener(this);
    	q11.setFont(myFont);
    	q12= new Button("Custom Query");
		jp2.add(q12);
		q12.setBackground(Color.darkGray.darker());
		q12.setForeground(Color.white.brighter());
    	q12.setBounds(750,400,120,40);
    	q12.addActionListener(this);
    	q12.setFont(myFont);
	}
	public void actionPerformed(ActionEvent ae) 
	{                String str = ae.getActionCommand();
	if(str.equals("Input Data"))
	{
		 Desktop desktop = Desktop.getDesktop();
	        File dirToOpen = null;
	        try
	        {
	            dirToOpen = new File("H:\\Sales Data");
	            desktop.open(dirToOpen);
	        }
	        catch (Exception iae)
	        {
	            System.out.println("File Not Found");
	        }
	        JOptionPane.showMessageDialog(null,"Place the input data file(.csv) and click OK!");
	}
	if(str.equals("Extract"))
	{
		Initialload il=new Initialload();
		il.extract();
		JOptionPane.showMessageDialog(null,"Data successfully Extracted.");
	}
	if(str.equals("Delete"))
	{
		try
		{
		Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
		Statement myst= mycon.createStatement();
		String infact="delete from DumpTable";
		myst.executeUpdate(infact);
		JOptionPane.showMessageDialog(null,"Dump Table data successfully deleted.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	if(str.equals("Transform"))
	{
		Initialload il2=new Initialload();
		il2.transform();
		JOptionPane.showMessageDialog(null,"Data successfully Transformed.");
	}
	if(str.equals("Load"))
	{
		Initialload il3=new Initialload();
		il3.load();
		JOptionPane.showMessageDialog(null,"Data successfully loaded into the Warehouse.");
	}
	if(str.equals("Queries"))
	{
		img=getImage(getCodeBase(),"b.jpg");
		this.removeAll();
		this.add(jp2);
		this.repaint();
		this.revalidate();
	}
	if(str.equals("< Back"))
	{
		
		img=getImage(getCodeBase(),"a.jpg");
		this.removeAll();
		this.add(jp1);
		this.repaint();
		this.revalidate();
	}
	if(str.equals("Total Sales"))
	{
		try
		{
		JPanel jp3=new JPanel();
		jt=new JTable();
		Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
		Statement myst= mycon.createStatement();
		String infact="Select sum(SalesTotalCost) as \"Total Sales\" from FactProductSales;";
		ResultSet rs=myst.executeQuery(infact);
		jt.setModel(DbUtils.resultSetToTableModel(rs));
		jp3.add(new JScrollPane(jt));
		
		JOptionPane.showConfirmDialog(null,
               jp3,
                "Total Sales ",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	if(str.equals("Net Profit"))
	{
		try
		{
		JPanel jp3=new JPanel();
		jt=new JTable();
		Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
		Statement myst= mycon.createStatement();
		String infact="select sum(Deviation) as \"Net Profit\" from FactProductSales";
		ResultSet rs=myst.executeQuery(infact);
		jt.setModel(DbUtils.resultSetToTableModel(rs));
		jp3.add(new JScrollPane(jt));
		
		JOptionPane.showConfirmDialog(null,
               jp3,
                "Net Profit",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	if(str.equals("Top Customers"))
	{
		try
		{
		JPanel jp3=new JPanel();
		jt=new JTable();
		Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
		Statement myst= mycon.createStatement();
		String infact="Select CustomerName,CustomerAltID,sum(SalesTotalCost) as \"Total Sales\""
				+ " from dimcustomer c,factproductsales f"
				+ " where c.CustomerID=f.CustomerID"
				+ " group by f.CustomerID"
				+ " order by sum(SalesTotalCost) desc"
				+ " limit 10";
		ResultSet rs=myst.executeQuery(infact);
		jt.setModel(DbUtils.resultSetToTableModel(rs));
		jp3.add(new JScrollPane(jt));
		
		JOptionPane.showConfirmDialog(null,
               jp3,
                "Top Customers",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	if(str.equals("Sales by Month"))
	{
		try
		{
		JPanel jp3=new JPanel();
		jt=new JTable();
		Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
		Statement myst= mycon.createStatement();
		String infact="Select month(SalesDate) as \"Month\",year(SalesDate) as \"Year\",sum(SalesTotalCost) as \"Total Sales\""
				+ " from factproductsales"
				+ " group by month(SalesDate)"
				+ " order by sum(SalesTotalCost) desc;";
		ResultSet rs=myst.executeQuery(infact);
		jt.setModel(DbUtils.resultSetToTableModel(rs));
		jp3.add(new JScrollPane(jt));
		
		JOptionPane.showConfirmDialog(null,
               jp3,
                "Sales by Month",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	if(str.equals("Sales by Country"))
	{
		try
		{
		JPanel jp3=new JPanel();
		jt=new JTable();
		Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
		Statement myst= mycon.createStatement();
		String infact=" Select Country,sum(SalesTotalCost) as \"Total Sales\""
				+ " from factproductsales f, dimstores s"
				+ " where f.storeId=s.StoreId"
				+ " group by Country"
				+ " order by sum(SalesTotalCost) desc;";
		ResultSet rs=myst.executeQuery(infact);
		jt.setModel(DbUtils.resultSetToTableModel(rs));
		jp3.add(new JScrollPane(jt));
		
		JOptionPane.showConfirmDialog(null,
               jp3,
                "Sales by Country",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	if(str.equals("Customer Details"))
	{
		try
		{
		JPanel jp3=new JPanel();
		jt=new JTable();
		Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
		Statement myst= mycon.createStatement();
		String infact="select *from DimCustomer";
		ResultSet rs=myst.executeQuery(infact);
		jt.setModel(DbUtils.resultSetToTableModel(rs));
		jp3.add(new JScrollPane(jt));
		
		JOptionPane.showConfirmDialog(null,
               jp3,
                "Customer Details",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	if(str.equals("Store Details"))
	{
		try
		{
		JPanel jp3=new JPanel();
		jt=new JTable();
		Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
		Statement myst= mycon.createStatement();
		String infact="select *from DimStores";
		ResultSet rs=myst.executeQuery(infact);
		jt.setModel(DbUtils.resultSetToTableModel(rs));
		jp3.add(new JScrollPane(jt));
		
		JOptionPane.showConfirmDialog(null,
               jp3,
                "Store Details",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	if(str.equals("Product Details"))
	{
		try
		{
		JPanel jp3=new JPanel();
		jt=new JTable();
		Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
		Statement myst= mycon.createStatement();
		String infact="select *from DimProduct";
		ResultSet rs=myst.executeQuery(infact);
		jt.setModel(DbUtils.resultSetToTableModel(rs));
		jp3.add(new JScrollPane(jt));
		
		JOptionPane.showConfirmDialog(null,
               jp3,
                "Product Details",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	if(str.equals("S.Person Details"))
	{
		try
		{
		JPanel jp3=new JPanel();
		jt=new JTable();
		Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
		Statement myst= mycon.createStatement();
		String infact="select *from DimSalesPerson";
		ResultSet rs=myst.executeQuery(infact);
		jt.setModel(DbUtils.resultSetToTableModel(rs));
		jp3.add(new JScrollPane(jt));
		
		JOptionPane.showConfirmDialog(null,
               jp3,
                "Sales Person Details",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	if(str.equals("Sales by Store"))
	{
		try
		{
		JPanel jp3=new JPanel();
		jt=new JTable();
		Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
		Statement myst= mycon.createStatement();
		String infact="Select StoreName,StoreLocation,City,State,Country,sum(SalesTotalCost) as \"Total Sales\""
				+ " from factproductsales f,dimstores s"
				+ " where s.StoreID=f.StoreID"
				+ " group by f.StoreId"
				+ " order by sum(SalesTotalCost) desc;";
		ResultSet rs=myst.executeQuery(infact);
		jt.setModel(DbUtils.resultSetToTableModel(rs));
		jp3.add(new JScrollPane(jt));
		
		JOptionPane.showConfirmDialog(null,
               jp3,
                "Sales by Store",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	if(str.equals("All Transactions"))
	{
		try
		{
		JPanel jp3=new JPanel();
		jt=new JTable();
		Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
		Statement myst= mycon.createStatement();
		String infact="select *from factproductsales";
		ResultSet rs=myst.executeQuery(infact);
		jt.setModel(DbUtils.resultSetToTableModel(rs));
		jp3.add(new JScrollPane(jt));
		
		JOptionPane.showConfirmDialog(null,
               jp3,
                "Transaction Details",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	if(str.equals("Custom Query"))
	{
		try
		{
		query=JOptionPane.showInputDialog("Enter your query:");
		JPanel jp3=new JPanel();
		jt=new JTable();
		Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
		Statement myst= mycon.createStatement();
		if(query!=null)
		{ResultSet rs=myst.executeQuery(query);
		jt.setModel(DbUtils.resultSetToTableModel(rs));
		jp3.add(new JScrollPane(jt));
		
		JOptionPane.showConfirmDialog(null,
               jp3,
                "Custom Query",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Invalid Query! Please try again.");
			e.printStackTrace();
		}
	}
	repaint();
	}
	public void paint(Graphics G)
	{
		
		G.drawImage(img,0,0,this);
		G.drawString(msg, 6, 100);
	}
}
class Initialload 
{
	void extract()
	{	
		try
		{
			Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
			Statement myst= mycon.createStatement();
			String path="H:/Sales Data/Book1.csv";
			String esquel = " LOAD DATA LOCAL INFILE '" + path +
                    "' INTO TABLE DumpTable " +
                    " FIELDS TERMINATED BY \',\' ENCLOSED BY \'\"'" +
                    " LINES TERMINATED BY \'\\n\'";
			myst.executeUpdate(esquel);
		}
		catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	void transform()
	{
		try
		{
			Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
			Statement myst= mycon.createStatement();
			String incus="insert into DimCustomer (CustomerAltID,CustomerName,Gender)" +
					 "select cust_id,initcap(cust_name),upper(cust_gen)"+ 
					 "from dumptable "+
					 "where not exists (select 1 from DimCustomer where cust_id=CustomerAltID)"+
					 " group by cust_id";
			myst.executeUpdate(incus);
			String insto="insert into DimStores (StoreAltID,StoreName, StoreLocation,City,State,Country)"
					+ " select store_id,initcap(store_name),store_loc,initcap(city),initcap(state),country from dumptable "
					+ "where not exists (select 1 from dimstores where store_id=StoreAltID)"
					+ " group by store_id";
			myst.executeUpdate(insto);
			String insper="insert into DimSalesPerson (SalesPersonAltID,SalesPersonName,"
					+ "StoreID,City,State,Country)"
					+ " select salesperson_id,initcap(salesperson),store_id,initcap(city),initcap(state),country"
					+ " from dumptable"
					+ " where not exists (select 1 from dimSalesPerson where salesperson_id=SalesPersonAltID)"
					+ " group by salesperson_id";
			myst.executeUpdate(insper);
			String inpro="insert into DimProduct (ProductAltKey,ProductName,ProductActualCost,ProductSalesCost)"
					+ " (select product_id,initcap(product_name),p_actualcost,p_salescost"
					+ " from dumptable"
					+ " where not exists (select 1 from DimProduct where ProductAltKey = product_id)"
					+ " and upper(country) like 'INDIA'"
					+ " group by product_id)"
					+ "	union"
					+ "	(select product_id,initcap(product_name),p_actualcost*65,p_salescost*65"
					+ "	from dumptable"
					+ "	where not exists (select 1 from DimProduct where ProductAltKey = product_id)"
					+ "	and upper(country) like 'USA'"
					+ "	group by product_id)";
			myst.executeUpdate(inpro);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void load()
	{
		try
		{
			Connection mycon= DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdw","root","god001");
			Statement myst= mycon.createStatement();
			String infact="insert into factproductsales (TransAltID,SalesDate,StoreID,"
					+ "CustomerID,ProductID,SalesPersonID,quantity,SalesTotalCost,ProductActualCost,Deviation)"
					+ "(select t_id,t_date,"
					+ "(select StoreID from dimstores where store_id=StoreAltID),"
					+ "(select CustomerID from dimcustomer where cust_id=CustomerAltID),"
					+ "(select ProductKey from dimproduct where product_id=ProductAltKey),"
					+ "(select SalesPersonID from dimsalesperson where salesperson_id = SalesPersonAltID),"
					+ "quantity,total_amount,p_actualcost,"
					+ "total_amount-(quantity*p_actualcost)"
					+ " from DumpTable"
					+ " where not exists (select 1 from factproductsales where t_id=TransAltID)"
					+ " and upper(country) like 'INDIA'"
					+ " group by t_id)"
					+ " union"
					+ "(select t_id,t_date,"
					+ "(select StoreID from dimstores where store_id=StoreAltID),"
					+ "(select CustomerID from dimcustomer where cust_id=CustomerAltID),"
					+ "(select ProductKey from dimproduct where product_id=ProductAltKey),"
					+ "(select SalesPersonID from dimsalesperson where salesperson_id = SalesPersonAltID),"
					+ "quantity,total_amount*65,p_actualcost*65,"
					+ "(total_amount*65)-(quantity*p_actualcost*65)"
					+ " from DumpTable"
					+ " where not exists (select 1 from factproductsales where t_id=TransAltID)"
					+ " and upper(country) like 'USA'"
					+ " group by t_id)";
			myst.executeUpdate(infact);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
