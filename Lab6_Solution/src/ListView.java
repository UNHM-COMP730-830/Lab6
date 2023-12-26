
public class ListView {
	
	private ListViewAdapter adapter;
	
	public ListView() {
	}
	
	public void setAdapter(ListViewAdapter adapter) {
		this.adapter = adapter;
	}

	public void updateData() {
		for (View view : adapter.getViews()) {
			view.update();
		}
	}
}
