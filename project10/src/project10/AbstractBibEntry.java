package project10;


/**
 * A parent class "AbstractBibEntry" for child classes
 * (in this case, it will be "BookBibEntry" and "JournalBibEntry"
 * but other child classes could be made) to utilize and expand/
 * improve upon
 * @author Justin Homme
 * @version 04/21/21
 *
 */
public abstract class AbstractBibEntry implements Comparable<AbstractBibEntry> {
	
	/**
	 * the title of the Citation
	 */
	protected String title;
	
	/**
	 * the first name of the 1st author
	 */
	protected String author1_fname;
	
	/**
	 * the last name of the 1st author
	 */
	protected String author1_lname;
	
	/**
	 * the first name of the 2nd author
	 */
	protected String author2_fname;
	
	/**
	 * the last name of the 2nd author
	 */
	protected String author2_lname;
	
	/**
	 * a boolean value which tells whether or not there 
	 * are more than two authors
	 */
	protected boolean etal;
	
	/**
	 * the year of publication
	 */
	protected int year;
	
	/**
	 * a constructor for class "AbstractBibEntry"
	 * @param title
	 * @param author1_fname 1st author's first name
	 * @param author1_lname 1st author's last name
	 * @param author2_fname 2nd author's first name
	 * @param author2_lname 2nd author's last name
	 * @param etal a boolean value which tells if there are more than 2 authors
	 * @param year the year it was published
	 * @throws IllegalArgumentException if the name of the title is null or an empty string,
	 * if author 1 or 2 has a legal first name without a legal last name, author 1 or 2 has 
	 * a legal last name without a legal first name, author 1 and 2 are null or empty and etal
	 * is set to true, or the year is negative
	 * @throws IllegalStateException if author one is null or empty but author 2 has a valid name 
	 */
	public AbstractBibEntry(String title, String author1_fname,
							String author1_lname, String author2_fname,
							String author2_lname, boolean etal, int year) throws IllegalArgumentException, IllegalStateException {
		setTitle(title);
		setAuthor1name(author1_fname, author1_lname);
		setAuthor2name(author2_fname, author2_lname);
		setEtal(etal);
		setYear(year);
	}
	/**
	 * if the title is null, we return an empty String to make compareTo() work below
	 * @return title the title
	 */
	public String getTitle() {
		if(title == null) {
			return "";
		}
		return title;
	}
	/**
	 * @param title the title to be set
	 * @throws IllegalArgumentException if the title is null or an empty string
	 */
	public void setTitle(String title) throws IllegalArgumentException {
		if(title == null || title.equals("")) {
			throw new IllegalArgumentException("The title cannot be null or an empty string.");
		}
		this.title = title;
	}
	/**
	 * if the title is null, we return an empty String to make compareTo() work below
	 * @return author1_fname the first name of author 1
	 */
	public String getAuthor1_fname() {
		if(author1_fname == null) {
			return "";
		}
		return author1_fname;
	}
	/**
	 * @param author1_fname, author1_lname the first name of the primary author
	 * and the last name of the primary author
	 * @throws IllegalArgumentException if the author has a legal first name without a legal last name or the author has 
	 * a legal last name without a legal first name
	 */
	public void setAuthor1name(String author1_fname, String author1_lname) throws IllegalArgumentException {
		if((author1_fname != null && !author1_fname.equals("")) && (author1_lname == null || author1_lname.equals(""))) {
			throw new IllegalArgumentException("The author cannot have a first name without a last name");
			// Author 1 has a legal first name but no legal last name
		}else if((author1_lname != null && !author1_lname.equals("")) && (author1_fname == null || author1_fname.equals(""))) {
			throw new IllegalArgumentException("The author cannot have a last name without a first name");
			// Author 1 has a legal last name but no legal first name
		}else if(((author1_fname == null || author1_fname.equals("")) && (author1_lname == null || author1_lname.equals("")))
				&& ((author2_fname != null && !author2_fname.equals("")) && (author2_lname != null && !author2_lname.equals("")))) {
			this.author1_fname = this.author2_fname;
			this.author1_lname = this.author2_lname;
			this.author2_fname = author1_fname;
			this.author2_lname = author1_lname;
			// Author 1 is null or blank but author 2 has a legal name
		}else if(((author1_fname == null || author1_fname.equals("")) && (author1_lname == null || author1_lname.equals(""))) && 
				((author2_fname == null || author2_fname.equals("")) && (author2_lname == null || author2_lname.equals("")))) {
			setEtal(false);
			this.author1_fname = author1_fname;
			this.author1_lname = author1_lname;
			// if Author 1 is null or blank and Author 2 is null or blank etal must be false
		}else {
			this.author1_fname = author1_fname;
			this.author1_lname = author1_lname;
		}
	}
	/**
	 * if the title is null, we return an empty String to make compareTo() work below
	 * @return author1_lname the last name of author 1
	 */
	public String getAuthor1_lname() {
		if(author1_lname == null) {
			return "";
		}
		return author1_lname;
	}
	/**
	 * if the title is null, we return an empty String to make compareTo() work below
	 * @return author2_fname the first name of author 2
	 */
	public String getAuthor2_fname() {
		if(author2_fname == null) {
			return "";
		}
		return author2_fname;
	}
	/**
	 * @param author2_fname, author2_lname the first name of the secondary
	 * author and the last name of the secondary author
	 * @throws IllegalStateException if the first author is null or empty and author 2 is not null or empty (since you cannot 
	 * have a second author without a first author) 
	 * @throws IllegalArgumentException if the first name of the second author is valid but the last name is null or an empty string 
	 * or if the last name of the second author is valid but the first name is null or an empty string
	 */
	public void setAuthor2name(String author2_fname, String author2_lname) throws IllegalStateException, IllegalArgumentException {
		if(((author1_fname == null || author1_fname.equals("")) && (author1_lname == null || author1_lname.equals(""))) && 
				((author2_fname != null && !author2_fname.equals("")) || (author2_lname != null && !author2_lname.equals("")))) {
			throw new IllegalStateException("You cannot set an author 2 without an author 1");
			// Author 2 has a legal name but Author 1 does not have a legal name
		}else if((author2_fname != null && !author2_fname.equals("")) && (author2_lname == null || author2_lname.equals(""))) {
			throw new IllegalArgumentException("The author cannot have a first name without a last name");
			// Author 2 has a legal first name but no legal last name
		}else if((author2_lname != null && !author2_lname.equals("")) && (author2_fname == null || author2_fname.equals(""))) {
			throw new IllegalArgumentException("The author cannot have a last name without a first name");
			// Author 2 has a legal last name but no legal first name
		}
		this.author2_fname = author2_fname;
		this.author2_lname = author2_lname;
	}
	/**
	 * if the title is null, we return an empty String to make compareTo() work below
	 * @return author2_lname the last name of author 2
	 */
	public String getAuthor2_lname() {
		if(author2_lname == null) {
			return "";
		}
		return author2_lname;
	}
	/**
	 * @return etal whether there is more than 2 authors or not
	 */
	public boolean isEtal() {
		return etal;
	}
	/**
	 * @param etal a boolean value which tells if there are more than 2 authors
	 * @Throws IllegalArgumentException if both author 1 and author 2 are null or empty and etal is true
	 */
	public void setEtal(boolean etal) throws IllegalArgumentException {
		if(((author1_fname == null || author1_fname.equals("")) && (author1_lname == null || author1_lname.equals(""))) && 
				((author2_fname == null || author2_fname.equals("")) && (author2_lname == null || author2_lname.equals("")))
				&& (etal == true)){
			throw new IllegalArgumentException("Etal cannot be true if both authors are null or blank");
			// Etal is set to true but both authors are null or blank
		}
		this.etal = etal;
	}
	/**
	 * @return year the year of publishing
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year it was published
	 * @throws IllegalArgumentException if the year is negative
	 */
	public void setYear(int year) {
		if(year < 0) {
			throw new IllegalArgumentException("The year cannot be negative");
			// the year is set to a negative value
		}
		this.year = year;
	}
	
	/**
	 * Creates two temporary Strings: thisString and otherString.
	 * The Strings contain each relevant attribute of each AbstractBibEntry 
	 * being compared. The getter methods ensure that we don't add any null pointers
	 * to our Strings. Then the built in compareTo() method (for Strings) is called
	 * @param other the "other" AbstractBibEntry we want to compare the original with
	 * @return an integer value based upon which order the AbstractBibEntries should
	 * be placed in
	 */
	@Override
	public int compareTo(AbstractBibEntry other) {
		
		String thisString = new String(this.getAuthor1_lname() + this.getAuthor1_fname() + this.getAuthor2_lname()
				+ this.getAuthor2_fname() + this.getTitle());
		//String for the "this" AbstractBibEntry
		
		String otherString = new String(other.getAuthor1_lname() + other.getAuthor1_fname() + other.getAuthor2_lname()
				+ other.getAuthor2_fname() + other.getTitle());
		//String for the "other" AbstractBibEntry
		
		return thisString.compareTo(otherString);  //Calls the built in compareTo() method and returns an integer value
	}
	
	/**
	 * a method that returns a bibliography entry in APA format
	 * @return aPAString a string that contains the bibliography entry in APA format
	 */
	public String getAPAString() {
		String aPAString = ""; //the string we will return at the end initialized as empty
		if(((author2_lname == null) || (author2_lname.equals(""))) && (isEtal() == false)) {
			aPAString = new String(getAuthor1_lname() + ", " + author1_fname.charAt(0) + "."); 
			//author 2 is null or blank and et al is false
		}else if(((author2_lname != null) && (!author2_lname.equals(""))) && (isEtal() == false)) {
			aPAString = new String(getAuthor1_lname() + ", " + author1_fname.charAt(0) + ". & " + getAuthor2_lname() + ", " 
					+ author2_fname.charAt(0) + ".");
			//author 2 is not null or empty but et al is false
		}else if(author2_lname == null || author2_lname.equals("") && isEtal() == true) {
			aPAString = new String(getAuthor1_lname() + ", " + author1_fname.charAt(0) + ". et al.");
			//author 2 is null or empty but et al is true
		}else {
			aPAString = new String(getAuthor1_lname() + ", " + author1_fname.charAt(0) + ". & " + getAuthor2_lname() + ", " 
					+ author2_fname.charAt(0) + ". et al.");
			//author 2 is not null or empty and et al is true
		}
		return aPAString;
	}
	
	/**
	 * a method that returns a bibliography entry in MLA format
	 * @return mLAString a string that contains the bibliography entry in MLA format
	 */
	public String getMLAString() {
		String mLAString = ""; //the string we will return at the end initialized as empty
		if(((author2_lname == null) || (author2_lname.equals(""))) && (isEtal() == false)) {
			mLAString = getAuthor1_lname() + ", " + getAuthor1_fname() + "."; 
			//author 2 is null or empty and et al is false
		}else if(((author2_lname != null) && (!author2_lname.equals(""))) && (isEtal() == false)) {
			mLAString = getAuthor1_lname() + ", " + getAuthor1_fname() + ", and " + getAuthor2_fname() + " " 
					+ getAuthor2_lname() + ".";
			//author 2 is not null or empty but et al is false
		}else if(((author2_lname == null) || (author2_lname.equals(""))) && (isEtal() == true)) {
			mLAString = getAuthor1_lname() + ", " + getAuthor1_fname() + ", et al.";
			//author 2 is null or empty but et al is true
		}else {
			mLAString = getAuthor1_lname() + ", " + getAuthor1_fname() + ", and " + getAuthor2_fname() + " " 
					+ getAuthor2_lname() + ", et al.";
			//author 2 is not null or empty and et al is true
		}
		return mLAString;
	}
	
}
