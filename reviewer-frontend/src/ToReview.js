import styles from './ToReview.module.css'

function PaperIdGroup() {
    return (
        <div className={styles.paperIdGroup}>
            <label className={styles.toReviewLabel} htmlFor="paperId">ID</label>
            <input className={styles.toReviewInput} id="paperId" type="text" placeholder="Paper id" readOnly />
        </div>
    );
}

function VerdictGroup() {
    return (
        <div className={styles.verdictGroup}>
            <label className={styles.toReviewLabel} htmlFor="verdict">Verdict</label>
            <input className={styles.toReviewInput} id="verdict" type="text" placeholder="accepted / rejected" readOnly />
            <button className={styles.toReviewButton} type="button">Submit</button>
        </div>
    );
}

function ConflictAuxiliaries() {
    return (
        <div className={styles.conflictAuxiliaries}>
            <label className={styles.conflictLabel} htmlFor="conflictTextarea">Conflict</label>
            <button className={styles.conflictButton} type="button">Submit</button>
        </div>
    );
}

function ConflictArea() {
    return (
        <textarea className={styles.conflictArea} id="conflictTextarea" placeholder="Conflict of interest description" />    
    );
}

function ConflictGroup() {
    return (
        <div className={styles.conflictGroup}>
            <ConflictAuxiliaries />
            <ConflictArea />
        </div>
    );
}

function ToReviewPannel() {
    return (
        <div className={styles.toReviewPannel}>
            <PaperIdGroup />
            <VerdictGroup />
            <ConflictGroup />
        </div>
    );
}

export { ToReviewPannel }