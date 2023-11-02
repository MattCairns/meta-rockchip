# Copyright (C) 2021, Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
require linux-rockchip.inc

inherit local-git

SRCREV = "1bd6972cd0093725c0b1dc87f6546648bbb22452"
SRC_URI = " \
	git://github.com/khadas/linux.git;protocol=https;nobranch=1;branch=khadas-linux-6.4.y; \
	file://${THISDIR}/files/cgroups.cfg \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "6.4"

SRC_URI:append = " ${@bb.utils.contains('IMAGE_FSTYPES', 'ext4', \
		   'file://${THISDIR}/files/ext4.cfg', \
		   '', \
		   d)}"

# do_patch:append() {
# 	sed -i 's/-I\($(BCMDHD_ROOT)\)/-I$(srctree)\/\1/g' \
# 		${S}/drivers/net/wireless/rockchip_wlan/rkwifi/bcmdhd/Makefile
# }
