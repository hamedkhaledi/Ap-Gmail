package Model.IO.ViewModel;

public enum MessageType {
    /**
     * connect to a user
     */
    Connect,
    /**
     * disconnect user from server
     */
    Disconnect,
    /**
     * send a text message
     */
    Send,
    Recieve,
    Error,
    SignUp,
    Forward,
    Change,
    Block,
    Delete, SignIn, Reload,
    Reply, Star, Read, Unread, File, Setting, OpenFile, UnBlock
}