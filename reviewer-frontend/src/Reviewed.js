import styles from './Reviewed.module.css'

function PaperIdGroup() {
    return (
        <div className={styles.paperIdGroup}>
            <label className={styles.reviewedLabel} for="paperId">ID</label>
            <input className={styles.reviewedInput} id="paperId" type="text" placeholder="Paper id" readOnly />
        </div>
    );
}

function VerdictGroup() {
    return (
        <div className={styles.verdictGroup}>
            <label className={styles.reviewedLabel} for="paperVerdict">Verdict</label>
            <input className={styles.reviewedInput} id="paperVerdict" type="text" placeholder="accepted / rejected" readOnly />
        </div>
    );
}

function AddModifButton() {
    return <button class={styles.reviewedButton} type="button">Add/Modif</button>
}

function DeleteButton() {
    return <button class={styles.reviewedButton} type="button">Delete</button>
}

function CommentArea() {
    return <textarea className={styles.commentArea} id="commentTextarea" placeholder="Special comment submitted by reviewer" />
}

function CommentAuxiliaries() {
    return (
        <div className={styles.commentAuxiliaries}>
            <label className={styles.commentLabel} for="commentTextarea">Comment</label>
            <AddModifButton />
            <DeleteButton />
        </div>
    );
}

function CommentGroup() {
    return (
        <div className={styles.commentContainer}>
            <CommentAuxiliaries />
            <CommentArea />
        </div>
    );
}

function ReviewedPannel() {
    return (
        <div className={styles.reviewedPannel}>
            <PaperIdGroup />
            <VerdictGroup />
            <CommentGroup />
        </div>
    );
}

export { ReviewedPannel }