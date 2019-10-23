package duke.command.orderCommand;

import duke.command.Cmd;
import duke.exception.DukeException;
import duke.list.GenericList;
import duke.order.Order;
import duke.order.OrderList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a specific {@link Cmd} used to alter the {@link Order} serving date.
 */
public class AlterServingDateCmd extends Cmd<Order> {

    private int orderNb;
    private String date;

    /**
     * The constructor method for {@link AlterServingDateCmd}.
     *
     * @param number order number
     * @param newDate new serving date of the {@link Order}
     */
    public AlterServingDateCmd(int number, String newDate) {
        this.orderNb = number;
        this.date = newDate;
    }

    @Override
    public void execute(GenericList<Order> orderList, Ui ui, Storage storage) throws DukeException {
        if (orderNb < orderList.size() && orderNb >= 0) {
            if (orderList.getEntry(orderNb).isDone()) {
                throw new DukeException("Order done already. No alteration is expected.");
            }
            ((OrderList)orderList).changeOrderDate(orderNb, date);
            ui.showOrderChangedDate(date,orderList.getEntry(orderNb).toString());
            storage.changeContent(orderNb);
        } else {
            throw new DukeException("Please enter a valid order number between 1 and "
                    + orderList.size() + " to alter the serving date");
        }
    }
}

