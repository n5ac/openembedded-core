SUMMARY = "New user to do specific job"
DESCRIPTION = "This recipe create a new user named ${PN}, who is used for specific jobs like building. The task can be auto started via mini X"
SECTION = "x11"
PR = "r0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://builder_hob_start.sh;endline=5;md5=84796c3c41785d86100fdabcbdade00e"

SRC_URI = "file://builder_hob_start.sh"

S = "${WORKDIR}"

RDEPENDS_${PN} = "mini-x-session"

inherit useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system --create-home \
                       --groups video,tty,audio \
                       --user-group ${PN}"

do_install () {
	install -d -m 755 ${D}/etc/mini_x/session.d
	install -p -m 755 builder_hob_start.sh ${D}/etc/mini_x/session.d/

	chown  ${PN}.${PN} ${D}/etc/mini_x/session.d/builder_hob_start.sh
}

