import styles from './Unrelated.module.css'

function BidGroup() {
    return (
        <div className={styles.bidGroup}>
            <label className={styles.bidLabel} htmlFor="bidIn">Bid</label>
            <input className={styles.bidInput} 
            id="bidIn" 
            type="input" 
            maxLength="3" 
            title="Interest, from 1 to 100"
            placeholder="Numeric value" />
        </div>
    );
}

function UnrelatedPannel() {
    return (
        <div className={styles.unrelatedPannel}>
            <BidGroup />
        </div>
    );
}

export { UnrelatedPannel }