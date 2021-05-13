package gb.cloud.domain;

/**
 * Перечень всех команд
 */

public enum CommandCode {
    AUTHENTICATE,
    REQUEST_FILE_LIST,
    CMD_SUCCESS,
    CMD_FAIL,
    DOWNLOAD,
    UPLOAD,
    FILES_REQUEST,
    FILES_OFFER,
    UPLOAD_READY,
    DOWNLOAD_READY,
    DOWNLOAD_POSSIBLE,
    UPLOAD_POSSIBLE
}
