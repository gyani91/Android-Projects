package com.example.testapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

	ArrayList<Integer> mSelectedItems;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// activity_main is in res/layout/activity_main.xml
		setContentView(R.layout.activity_main);

		try {

			// Click listener.
			View.OnClickListener handler = new View.OnClickListener() {
				public void onClick(View v) {

					switch (v.getId()) {
					
					case R.id.alertOneBtn:
						alertOneButton();
						break;
					
					case R.id.alertTwoBtn:
						// alertTwoButtons();
						alertTwoButtons();
						break;

					case R.id.alertThreeBtn:
						alertThreeButtons();
						break;

					case R.id.alertTimePickerBtn:
						alertTimePicker();
						break;

					case R.id.alertDatePickerBtn:
						alertDatePicker();
						break;

					case R.id.alertSimpleListViewBtn:
						alertSimpleListView();
						break;

					case R.id.alertScrollViewBtn:
						alertScrollView();
						break;

					case R.id.alertFormElementsBtn:
						alertFormElements();
						break;

					case R.id.alertWebViewBtn:
						alertWebView();
						break;

					case R.id.alertMultiChoiceBtn:
						
						alertMultipleChoiceItems();
						break;
						
					case R.id.alertSingleChoiceBtn:
						
						alertSingleChoiceItems();
						break;
						
					case R.id.alertCustomizedLayoutBtn:
						
						alertCustomizedLayout();
						break;
					
					case R.id.alertEditTextKeyboardShownBtn:
						
						alertEditTextKeyboardShown();
						break;
						
					case R.id.alertPersistentDialogBtn:
						
						alertPersistentDialog();
						break;
						
					}
				}
			};
			
			// Set button listeners.
			findViewById(R.id.alertOneBtn).setOnClickListener(handler);
			findViewById(R.id.alertTwoBtn).setOnClickListener(handler);
			findViewById(R.id.alertThreeBtn).setOnClickListener(handler);
			findViewById(R.id.alertTimePickerBtn).setOnClickListener(handler);
			findViewById(R.id.alertDatePickerBtn).setOnClickListener(handler);
			findViewById(R.id.alertSimpleListViewBtn).setOnClickListener(handler);
			findViewById(R.id.alertScrollViewBtn).setOnClickListener(handler);
			findViewById(R.id.alertFormElementsBtn).setOnClickListener(handler);
			findViewById(R.id.alertWebViewBtn).setOnClickListener(handler);
			findViewById(R.id.alertMultiChoiceBtn).setOnClickListener(handler);
			findViewById(R.id.alertSingleChoiceBtn).setOnClickListener(handler);
			findViewById(R.id.alertCustomizedLayoutBtn).setOnClickListener(handler);
			findViewById(R.id.alertEditTextKeyboardShownBtn).setOnClickListener(handler);
			findViewById(R.id.alertPersistentDialogBtn).setOnClickListener(handler);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Used to show toasts.
	 */
	public void showToast(String customText) {
		Toast.makeText(MainActivity.this, customText.trim(), Toast.LENGTH_LONG)
				.show();
	}

	/*
	 * AlertDialog with one button.
	 */
	public void alertOneButton() {
	
		new AlertDialog.Builder(MainActivity.this)
				.setTitle("One Button")
				.setMessage("The Code of a Ninja is your new favorite website.")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						showToast("Thank you!");
						dialog.cancel();
					}
				}).show();
	}

	/*
	 * AlertDialog with two button choices.
	 * 
	 * We also set the ninja icon here.
	 */
	public void alertTwoButtons() {
		new AlertDialog.Builder(MainActivity.this)
				.setTitle("Two Buttons")
				.setMessage("Do you think Mike is awesome?")
				.setIcon(R.drawable.ninja)
				.setPositiveButton("YES",
						new DialogInterface.OnClickListener() {
							@TargetApi(11)
							public void onClick(DialogInterface dialog, int id) {
								showToast("Thank you! You're awesome too!");
								dialog.cancel();
							}
						})
				.setNegativeButton("NO", new DialogInterface.OnClickListener() {
					@TargetApi(11)
					public void onClick(DialogInterface dialog, int id) {
						showToast("Mike is not awesome for you. :(");
						dialog.cancel();
					}
				}).show();
	}

	/*
	 * AlertDialog with three button choices.
	 * 
	 * We also set the ninja icon here.
	 */
	public void alertThreeButtons() {
		new AlertDialog.Builder(MainActivity.this)
				.setTitle("Three Buttons")
				.setMessage("Where do you want to go?")
				.setIcon(R.drawable.ninja)
				.setPositiveButton("RIGHT",
						new DialogInterface.OnClickListener() {
							@TargetApi(11)
							public void onClick(DialogInterface dialog, int id) {
								showToast("You want to go to the RIGHT.");

								dialog.cancel();
							}
						})
				.setNeutralButton("CENTER",
						new DialogInterface.OnClickListener() {
							@TargetApi(11)
							public void onClick(DialogInterface dialog, int id) {
								showToast("You want to go to the CENTER.");
								dialog.cancel();
							}
						})
				.setNegativeButton("LEFT",
						new DialogInterface.OnClickListener() {
							@TargetApi(11)
							public void onClick(DialogInterface dialog, int id) {
								showToast("You want to go to the LEFT.");
								dialog.cancel();
							}
						}).show();
	}

	/*
	 * Show AlertDialog with time picker.
	 */
	public void alertTimePicker() {

		/*
		 * Inflate the XML view. activity_main is in res/layout/time_picker.xml
		 */
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.time_picker, null, false);

		// the time picker on the alert dialog, this is how to get the value
		final TimePicker myTimePicker = (TimePicker) view
				.findViewById(R.id.myTimePicker);

		/*
		 * To remove option for AM/PM, add the following line:
		 * 
		 * operatingHoursTimePicker.setIs24HourView(true);
		 */

		// the alert dialog
		new AlertDialog.Builder(MainActivity.this).setView(view)
				.setTitle("Set Time")
				.setPositiveButton("Go", new DialogInterface.OnClickListener() {
					@TargetApi(11)
					public void onClick(DialogInterface dialog, int id) {

						String currentHourText = myTimePicker.getCurrentHour()
								.toString();

						String currentMinuteText = myTimePicker
								.getCurrentMinute().toString();

						// We cannot get AM/PM value since the returning value
						// will always be in 24-hour format.

						showToast(currentHourText + ":" + currentMinuteText);

						dialog.cancel();

					}

				}).show();
	}

	/*
	 * Show AlertDialog with date picker.
	 */
	public void alertDatePicker() {
	
		/*
		 * Inflate the XML view. activity_main is in res/layout/date_picker.xml
		 */
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.date_picker, null, false);
	
		// the time picker on the alert dialog, this is how to get the value
		final DatePicker myDatePicker = (DatePicker) view.findViewById(R.id.myDatePicker);
		
		// so that the calendar view won't appear
		myDatePicker.setCalendarViewShown(false);
		
		// the alert dialog
		new AlertDialog.Builder(MainActivity.this).setView(view)
				.setTitle("Set Date")
				.setPositiveButton("Go", new DialogInterface.OnClickListener() {
					@TargetApi(11)
					public void onClick(DialogInterface dialog, int id) {
	
						/*
						 * In the docs of the calendar class, January = 0, so we
						 * have to add 1 for getting correct month.
						 * http://goo.gl/9ywsj
						 */
						int month = myDatePicker.getMonth() + 1;
						int day = myDatePicker.getDayOfMonth();
						int year = myDatePicker.getYear();
	
						showToast(month + "/" + day + "/" + year);
	
						dialog.cancel();
	
					}
	
				}).show();
	}

	/*
	 * Show AlertDialog with a simple list view.
	 * 
	 * No XML needed.
	 */
	public void alertSimpleListView() {

		/*
		 * WebView is created programatically here.
		 * 
		 * @Here are the list of items to be shown in the list
		 */
		final CharSequence[] items = { "John", "Michael", "Vincent", "Dalisay" };

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("Make your selection");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {

				// will toast your selection
				showToast("Name: " + items[item]);
				dialog.dismiss();

			}
		}).show();
	}

	/*
	 * Show AlertDialog with ScrollView.
	 * 
	 * We use a TextView as ScrollView's child/host
	 */
	public void alertScrollView() {

		/*
		 * Inflate the XML view.
		 * 
		 * @activity_main is in res/layout/scroll_text.xml
		 */
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View myScrollView = inflater.inflate(R.layout.scroll_text, null, false);

		// textViewWithScroll is the name of our TextView on scroll_text.xml
		TextView tv = (TextView) myScrollView
				.findViewById(R.id.textViewWithScroll);

		// Initializing a blank textview so that we can just append a text later
		tv.setText("");

		/*
		 * Display the text 10 times so that it will exceed the device screen
		 * height and be able to scroll
		 */
		for (int x = 1; x < 50; x++) {
			tv.append("You've been HACKED!\n");
			tv.append("By NINJAZHAI.\n");
			tv.append("Just kidding.\n\n");
		}

		new AlertDialog.Builder(MainActivity.this).setView(myScrollView)
				.setTitle("Scroll View")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@TargetApi(11)
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}

				}).show();

	}

	/*
	 * Show AlertDialog with some form elements.
	 */
	public void alertFormElements() {

		/*
		 * Inflate the XML view. activity_main is in
		 * res/layout/form_elements.xml
		 */
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View formElementsView = inflater.inflate(R.layout.form_elements,
				null, false);

		// You have to list down your form elements
		final CheckBox myCheckBox = (CheckBox) formElementsView
				.findViewById(R.id.myCheckBox);

		final RadioGroup genderRadioGroup = (RadioGroup) formElementsView
				.findViewById(R.id.genderRadioGroup);

		final EditText nameEditText = (EditText) formElementsView
				.findViewById(R.id.nameEditText);

		// the alert dialog
		new AlertDialog.Builder(MainActivity.this).setView(formElementsView)
				.setTitle("Form Elements")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@TargetApi(11)
					public void onClick(DialogInterface dialog, int id) {

						String toastString = "";

						/*
						 * Detecting whether the checkbox is checked or not.
						 */
						if (myCheckBox.isChecked()) {
							toastString += "Happy is checked!\n";
						} else {
							toastString += "Happy IS NOT checked.\n";
						}

						/*
						 * Getting the value of selected RadioButton.
						 */
						// get selected radio button from radioGroup
						int selectedId = genderRadioGroup
								.getCheckedRadioButtonId();

						// find the radiobutton by returned id
						RadioButton selectedRadioButton = (RadioButton) formElementsView
								.findViewById(selectedId);

						toastString += "Selected radio button is: "
								+ selectedRadioButton.getText() + "!\n";

						/*
						 * Getting the value of an EditText.
						 */
						toastString += "Name is: " + nameEditText.getText()
								+ "!\n";

						showToast(toastString);

						dialog.cancel();
					}

				}).show();
	}

	/*
	 * Show AlertDialog with web view.
	 * 
	 * Don't forget the Internet permission on your AndroidManifest.xml
	 */
	public void alertWebView() {

		// WebView is created programatically here.
		WebView myWebView = new WebView(MainActivity.this);
		myWebView.loadUrl("http://google.com/");

		/*
		 * This part is needed so it won't ask the user to open another browser.
		 */
		myWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});

		new AlertDialog.Builder(MainActivity.this).setView(myWebView)
				.setTitle("My Web View")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@TargetApi(11)
					public void onClick(DialogInterface dialog, int id) {

						dialog.cancel();

					}

				}).show();
	}
	
	public void alertMultipleChoiceItems(){
		
		// where we will store or remove selected items
		mSelectedItems = new ArrayList<Integer>();  
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
	    
	    // set the dialog title
	    builder.setTitle("Choose One or More")
	    
		// specify the list array, the items to be selected by default (null for none),
		// and the listener through which to receive call backs when items are selected
	    // R.array.choices were set in the resources res/values/strings.xml
	    .setMultiChoiceItems(R.array.choices, null, new DialogInterface.OnMultiChoiceClickListener() {
	
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				
				if (isChecked) {
					// if the user checked the item, add it to the selected items
					mSelectedItems.add(which);
				} 
			
				else if (mSelectedItems.contains(which)) {
					// else if the item is already in the array, remove it 
					mSelectedItems.remove(Integer.valueOf(which));
				}
				
				// you can also add other codes here, 
				// for example a tool tip that gives user an idea of what he is selecting
				// showToast("Just an example description.");
			}
	
	    })
	           
		 // Set the action buttons
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				
				// user clicked OK, so save the mSelectedItems results somewhere
				// here we are trying to retrieve the selected items indices
				String selectedIndex = "";
				for(Integer i : mSelectedItems){
		        	selectedIndex += i + ", ";
		        }
				
				showToast("Selected index: " + selectedIndex);
			
			}
		})
		
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				// removes the AlertDialog in the screen
			}
		})
		
		.show();
	    
	}
	
	public void alertSingleChoiceItems(){
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
	    
	    // Set the dialog title
	    builder.setTitle("Choose One")
	    
		// specify the list array, the items to be selected by default (null for none),
		// and the listener through which to receive call backs when items are selected
	    // again, R.array.choices were set in the resources res/values/strings.xml
	    .setSingleChoiceItems(R.array.choices, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				showToast("Some actions maybe? Selected index: " + arg1);
			}
	
	    })
	           
		 // Set the action buttons
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				// user clicked OK, so save the mSelectedItems results somewhere
				// or return them to the component that opened the dialog
				
				int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
				showToast("selectedPosition: " + selectedPosition);
				
			}
		})
		
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				// removes the dialog from the screen
				
			}
		})
		
		.show();
	    
	}
	
	public void alertCustomizedLayout(){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
	
		// get the layout inflater
		LayoutInflater inflater = MainActivity.this.getLayoutInflater();
	
		// inflate and set the layout for the dialog
		// pass null as the parent view because its going in the dialog layout
		builder.setView(inflater.inflate(R.layout.login, null))
		
		// action buttons
		.setPositiveButton("Login", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				// your sign in code here
			}
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// remove the dialog from the screen
			}
		})
		.show();  
	    
	}
	
	
	public void alertEditTextKeyboardShown(){
		
		// creating the EditText widget programatically
		EditText editText = new EditText(MainActivity.this);
		
		// create the AlertDialog as final
		final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
			.setMessage("You are ready to type")
			.setTitle("The Code of a Ninja")
			.setView(editText)
			
			 // Set the action buttons
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int id) {
			        
			    }
			})
			
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
			    // removes the AlertDialog in the screen
			    }
			})
			.create();
		
		// set the focus change listener of the EditText
		// this part will make the soft keyboard automaticall visible
		editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
				}
			}
		});
		
		dialog.show();
	
	}
	
	public void alertPersistentDialog(){
		
		final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
			.setTitle("The Code of a Ninja")
			.setMessage("This is a persistent AlertDialog")
			.setPositiveButton("Show Toast", null) // null to override the onClick
			.setNegativeButton("Dismiss", null)
			.setCancelable(false)
			.create();

		alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
	
			@Override
			public void onShow(DialogInterface dialog) {
	
				Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
				btnPositive.setOnClickListener(new View.OnClickListener() {
	
					@Override
					public void onClick(View view) {
	
						showToast("Dialog not dismissed!");
						
					}
				});
				
				
				Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
				btnNegative.setOnClickListener(new View.OnClickListener() {
	
					@Override
					public void onClick(View view) {
	
						// dismiss once everything is ok
						alertDialog.dismiss();
					}
				});
			}
		});
		
		// don't forget to show it
		alertDialog.show();
			
	}
	
}
