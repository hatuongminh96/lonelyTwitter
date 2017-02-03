package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * The type Lonely twitter activity.
 * This class is the main view class of the project. <br> In this class,
 * user interaction and manipulation is performed.
 * All files in the form of json are stored in Emulator's storage are accessible in ADM
 * <pre>
 *     pre-format text: <br>
 *         File Explorer > data > data > ca.ualberta.cs.lonelytwitter >file
 * </pre>
 * <code> begin <br>
 *     some code <br>
 *         end
 *         </code>
 * The file name is indicated in the &nbsp &nbsp &nbsp  FILENAME constant.
 * <ul>
 *     <li> item 1</li>
 *     <li> item 2</li>
 *     <li> item 3</li>
 * </ul>
 *  <ol>
 *     <li> item 1</li>
 *     <li> item 2</li>
 *     <li> item 3</li>
 * </ol>
 *
 * @author ABCD
 * @version 1.4.2
 * @since 1.0
 */
public class LonelyTwitterActivity extends Activity {

	/**
	 * The file that all tweet is saved
	 * Format in JSON
	 * @see #loadFromFile()
	 * @see #saveInFile()
	 */
	private static final String FILENAME = "jsonfile.sav";
	private EditText bodyText;
	private enum TweetListOrdering {DATE_ASCENDING, DATE_DESCENDING, TEXT_ASCENDING,
		TEXT_DESCENDING}
	private ListView oldTweetsList;

	private ArrayList<Tweet> tweetList;
	private ArrayAdapter<Tweet> adapter;
	
	/** Called when the activity is first created.
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				text = TrimExtraSpace(text);
				Tweet tweet = new NormalTweet(text);
				tweetList.add(tweet);
				adapter.notifyDataSetChanged();
				saveInFile();
			}
		});

		clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				tweetList.clear();
				saveInFile();
				adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		//tweetList = new ArrayList<Tweet>();
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweetList);
		oldTweetsList.setAdapter(adapter);
	}

	/**
	 * Load tweets from file
	 * @throws TweetTooLongException if it's too long
	 * @exception FileNotFoundException if file was not created
	 */
	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			Gson gson = new Gson();
			tweetList=gson.fromJson(in, new TypeToken<ArrayList<NormalTweet>>(){}.getType());
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			tweetList=new ArrayList<Tweet>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	/**
	 * Save tweets in json format
	 * @throws FileNotFoundException if file was not created
	 */
	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

			Gson gson = new Gson();
			gson.toJson(tweetList, out);
			out.flush();

			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}


	/**
	 *
	 * @param inputS
	 * @return the String with characters from 0 to 140
     */
	private String TrimExtraSpace(String inputS){
		return inputS.substring(0,140);
	}

	private void sortTweetListItems(TweetListOrdering ordering){

	}
}