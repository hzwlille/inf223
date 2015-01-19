package inf223;


class Agent extends Thread {
    private String name;
    private int self;

    private int lastOwner;
    //  The last node requesting the lock

    private int nextOwner;
    //  The node waiting for the lock as soon as it is available

    private Proxy proxy;

    Agent (int self, int owner, Proxy proxy) {
        this.name = Node.table [self].name + " (agent)";
        this.self = self;
        this.lastOwner = owner;
        this.nextOwner = Node.none;
        this.proxy = proxy;

        Context.debug ("create " + this);
    }

    public String toString () {
        return this.name
            + " [lastOwner " + this.lastOwner
            + " nextOwner " + this.nextOwner
            + "]";
    }

    public void run () {
        Message m;
        Message r;

        try {
            while (true) {
                m = Channel.recv (self);
                Context.debug (this + " recv [" + m + "]");

                switch (m.kind) {
                case Message.receiveLock:
                    //  The agent can receive the lock for two reasons.
                    //
                    //  1/ The agent of the current node has previously
                    //  sent a request to get the lock back and the
                    //  previous owner is transfering the lock.
                    //
                    //  2/ This message is a reply from the proxy to
                    //  the agent for giving back the lock in order to
                    //  transfer as requested by another node.
                    //
                    //  The value m.from provides the origin of this
                    //  message.

                    if (m.from != self) {
                        //  The agent receives the lock previously
                        //  owned by another node. This means that the
                        //  current node has previously sent a request
                        //  to get the lock back. Deliver the lock to
                        //  the proxy.



                        //  Check that while the current node was waiting
                        //  for the lock, another node has not requested it.
                        //  If this is not the case, get next request.



                        //  There was a concurrent request to transfer
                        //  the lock to another node. But this request
                        //  was not fully addressed since the node was
                        //  not yet the owner. Once delivered, try to
                        //  get the lock back from the proxy. If it is
                        //  not yet possible, move the proxy status to
                        //  requested and get next request.
                        //  Otherwise, proceed to the move of the lock.



                    }

                    //  At this point, we can assert the lock has been
                    //  released by the proxy. Send it to the next
                    //  owner. The next owner is not Node.none since a
                    //  request has been made.







                    break;

                case Message.requestLock:
                    if (m.from == self) {
                        //  This request for lock comes from proxy. So
                        //  forward the request to the last owner and
                        //  declare node as new last owner.
                    } else if (lastOwner != self) {
                        //  Forward request to last owner since the
                        //  current node is not the last owner.
                    } else {
                        //   The current node is the last owner. It has to
                        //   get back the lock from the proxy.
                        if (proxy.request ()) {
                            //  If the proxy accepts to release the lock
                            //  immediatly, then forward the lock to the
                            //  node requesting it.
                        } else {
                            //  It not, then wait for proxy to notify it
                            //  released the lock.
                        }
                    }
                }
            }
        } catch (Exception e) {
            Context.panic ("Agent.run ()", e);
        }
    }
}
