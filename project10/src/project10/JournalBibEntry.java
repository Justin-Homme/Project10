package project10;

/**
 * a child class ("JournalBibEntry") of its parent class
 * ("AbstractBibEntry") that adds some attributes to 
 * expand upon/ improve its parent class
 * @author Justin Homme
 * @version 04/21/21
 *
 */
public class JournalBibEntry extends AbstractBibEntry {
	
	/**
	 * the name of the journal
	 */
	private String journal;
	
	/**
	 * the DOI code of the journal article
	 */
	private String doi;
	
	/**
	 * the issue number of the journal article
	 */
	private int issue;
	
	/**
	 * the volume number of the journal article
	 */
	private int volume;
	
	/**
	 * the starting page number of the journal article's
	 * citation
	 */
	private int pageStart;
	
	/**
	 * the ending page number of the journal article's
	 * citation
	 */
	private int pageEnd;
	
	/**
	 * @param title the title of the citation
	 * @param author1_fname 1st author's first name
	 * @param author1_lname 1st author's last name
	 * @param author2_fname 2nd author's first name
	 * @param author2_lname 2nd author's last name
	 * @param etal a boolean value that tells us if there are more than 2 authors
	 * @param journal the name of the journal article
	 * @param year the year in which the journal article was published
	 * @param issue the issue number of the journal article
	 * @param volume the volume number of the journal article
	 * @param pageStart the starting page number of the journal article's
	 * @param pageEnd the ending page number of the journal article's
	 * @param doi the DOI code of the journal article
	 * @throws IllegalArgumentException if the journal name is null or empty, the DOI is null or empty,
	 * the volume number is negative, the issue number is negative, the page start or end is negative, 
	 * or the page start is greater than the page end
	 */
	public JournalBibEntry(String title, String author1_fname,
							String author1_lname, String author2_fname,
							String author2_lname, boolean etal,
							String journal, int year, int issue,
							int volume, int pageStart, int pageEnd,
							String doi) throws IllegalArgumentException {
		
		super(title, author1_fname, author1_lname, author2_fname,		// allows us to inherit from
			  author2_lname, etal, year);								// the parent class
		
		setJournal(journal);
		setIssue(issue);
		setVolume(volume);
		setPages(pageStart, pageEnd);
		setDoi(doi);
	}
	/**
	 * @return the name of the journal
	 */
	public String getJournal() {
		return journal;
	}
	
	/**
	 * @param journal the name of the journal
	 * @throws IllegalArgumentException if the journal's title is null or empty
	 */
	public void setJournal(String journal) throws IllegalArgumentException {
		if(journal == null || journal.equals("")) {
			throw new IllegalArgumentException("The name of the jornal cannot be null or empty");
			// The journal's name is null or empty
		}
		this.journal = journal;
	}
	
	/**
	 * @return doi
	 */
	public String getDoi() {
		return doi;
	}
	
	/**
	 * @param doi the DOI code of the article
	 * @throws IllegalArgumentException if the DOI is null or empty
	 */
	public void setDoi(String doi) throws IllegalArgumentException {
		if(doi == null || doi.equals("")) {
			throw new IllegalArgumentException("The Doi cannot be null or empty");
			// The doi is set to null or empty
		}
		this.doi = doi;
	}
	
	/**
	 * @return issue
	 */
	public int getIssue() {
		return issue;
	}
	
	/**
	 * @param issue the issue number of the article
	 * @throws IllegalArgumentException if the issue number is negative
	 */
	public void setIssue(int issue) throws IllegalArgumentException{
		if(issue < 0) {
			throw new IllegalArgumentException("The issue number cannot be negative");
		}
		this.issue = issue;
	}
	
	/**
	 * @return volume
	 */
	public int getVolume() {
		return volume;
	}
	
	/**
	 * @param volume the volume number of the article
	 * @throws IllegalArgumentException if the volume number is negative
	 */
	public void setVolume(int volume) throws IllegalArgumentException {
		if(volume < 0) {
			throw new IllegalArgumentException("The volume number cannot be negative");
		}
		this.volume = volume;
	}

	/**
	 * @return pageStart
	 */
	public int getPageStart() {
		return pageStart;
	}

	/**
	 * sets pageStart and pageEnd in the same Setter in order
	 * to allow the main to call one Setter method with both
	 * the start and end page numbers
	 * @param pageStart the starting page of the citation
	 * @param pageEnd the ending page of the citation
	 * @throws IllegalArgumentException if either the starting page or the ending page is
	 * negative or if the starting page is greater than the ending page
	 */
	public void setPages(int pageStart, int pageEnd) throws IllegalArgumentException {
		if(pageStart < 0 || pageEnd < 0) {
			throw new IllegalArgumentException("The Page numbers may not be negative");
			// Either the starting page or the ending page is negative
		}else if(pageStart > pageEnd) {
			throw new IllegalArgumentException("The Starting page must be less than or equal"
					+ " to the ending page");
			// The starting page is greater than the ending page
		}
		this.pageStart = pageStart;
		this.pageEnd = pageEnd;
	}
	
	/**
	 * @return pageEnd
	 */
	public int getPageEnd() {
		return pageEnd;
	}
	
	/**
	 * an updated getAPAString() method for Journals
	 * @return aPAString a String containing the bibliography entry for a Journal in APA format
	 */
	@Override
	public String getAPAString() {
		String aPAString = ""; //the String that will be returned initialized to be empty
		if(author1_lname == null || author1_lname.equals("")) {
			aPAString = getTitle() + ". (" + getYear() + "). " + getJournal() + ", " + getIssue() + "(" + getVolume() + 
					"), " + getPageStart() + "-" + getPageEnd() + ". doi:" + getDoi();
			//author1 is null or empty (and thus author2 is null or empty)
		}else {
			aPAString = super.getAPAString() + " (" + getYear() + "). " + getTitle() + ". " + getJournal() + ", " + getIssue() + 
					"(" + getVolume() + "), " + getPageStart() + "-" + getPageEnd() + ". doi:" + getDoi();
			//there is at least one author so we use super.getAPAString()
		}
		return aPAString;
	}
	
	/**
	 * an updated getMLAString() method for Journals
	 * @return mLAString a String containing the bibliography entry for a Journal in MLA format
	 */
	@Override
	public String getMLAString() {
		String mLAString = ""; //the String that will be returned initialized to be empty
		if(author1_lname == null || author1_lname.equals("")) {
			mLAString = '"' + getTitle() + "." + '"' + " " + getJournal() + ", " + getIssue() + "." + getVolume() + " (" + 
					getYear() + "): " + getPageStart() + "-" + getPageEnd() + ". Print.";
			//author1 is null or empty (and thus author 2 is null or empty)
		}else {
			mLAString = super.getMLAString() + " " + '"' + getTitle() + "." + '"' + " " + getJournal() + ", " + getIssue() + "." + getVolume() + " (" + 
					getYear() + "): " + getPageStart() + "-" + getPageEnd() + ". Print.";
			//author1 is not null or empty so we call super.getMLAString()
		}
		return mLAString;
	}
	
}
