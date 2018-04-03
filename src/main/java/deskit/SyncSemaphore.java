package deskit;

class SyncSemaphore extends Object {

	/** Creates a new instance of BinarySemaphor */
	public SyncSemaphore() {
	}

	public void waitOnSem() {
		synchronized (this) {
			try {
				wait();
			} catch (Exception e) {
				System.err.println("ERROR synchronization wait : " + e.getMessage());
			}
		}
	}

	public void signalSem() {
		synchronized (this) {
			notify();
		}
	}
}