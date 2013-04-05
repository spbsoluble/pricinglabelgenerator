import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;

public class dndtest extends JLabel implements DropTargetListener, DragSourceListener, DragGestureListener
{
	DropTarget dropTarget = new DropTarget (this, this);
	DragSource dragSource = DragSource.getDefaultDragSource();

	public dndtest()
	{
		this.setText("Test Drag");
		dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY_OR_MOVE, this);
	}

	public void dragEnter(DropTargetDragEvent dtde) 
	{
		dtde.acceptDrag (DnDConstants.ACTION_COPY_OR_MOVE);
	}

	public void dragOver(DropTargetDragEvent dtde) {
	}

	public void dropActionchanged(DropTargetDragEvent dtde) {
	}

	public void dragExit(DropTargetEvent dte) {
	}

	public synchronized void drop(DropTargetDropEvent dtde)
	{
		try
		{
			Transferable tr = dtde.getTransferable();
			if (tr.isDataFlavorSupported (DataFlavor.javaFileListFlavor))
			{
				dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
				Object o =  tr.getTransferData(DataFlavor.javaFileListFlavor);
				char[] array = o.toString().toCharArray();
				String path = "";
				for(int i = 0; i < array.length - 1;i++)
				{
					path = path + array[i];
				}
				File file = new File(path);
				System.out.println(file.getAbsolutePath());
				dtde.getDropTargetContext().dropComplete(true);
			}
			else
			{
				System.out.print("Rejected");
				dtde.rejectDrop();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void dragEnter(DragSourceDragEvent dsde) {
	}

	public void dragOver(DragSourceDragEvent dsde) {
	}

	public void dropActionchanged(DragSourceDragEvent dsde) {
	}

	public void dragExit(DragSourceEvent dse) {
	}

	public void dragDropEnd(DragSourceDropEvent dsde) {
	}

	public void dragGestureRecognized(DragGestureEvent dge) 
	{
		
	}

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
